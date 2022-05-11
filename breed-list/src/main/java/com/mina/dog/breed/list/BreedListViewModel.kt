package com.mina.dog.breed.list

import android.util.Log
import androidx.lifecycle.*
import com.mina.dog.breed.common.models.Breed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
internal class BreedListViewModel @Inject constructor(
    private val breedListRepository: BreedListRepository,
    private val breedImageRepository: BreedImageRepository
) :
    ViewModel(),
    DefaultLifecycleObserver {

    private val _viewState: MutableStateFlow<ViewState> = MutableStateFlow(ViewState.Loading)
    val viewState: Flow<ViewState> get() = _viewState


    override fun onCreate(owner: LifecycleOwner) {
        _viewState.value = ViewState.Loading
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _viewState.value = when (val result = breedListRepository.loadBreeds()) {
                    is BreedListRepository.BreedListRepositoryResult.Success -> {
                        val breedsWithUpdatedImages = updateImages(result.breeds)
                        ViewState.Content(breedsWithUpdatedImages)
                    }
                    is BreedListRepository.BreedListRepositoryResult.Error -> ViewState.Error
                }
            }
        }
    }

    private suspend fun updateImages(breeds: List<Breed>): List<Breed> {
        val breedsWithImages: List<Breed> = try {
            breeds
                .filter { it.images.isEmpty() }
                .run { breedImageRepository.images(this) }
        } catch (e: Exception) {
            Log.e("Error", e.localizedMessage)
            emptyList()
        }

        return breeds
            .filterNot { it.images.isEmpty() } + breedsWithImages
            .sortedBy { it.name }
    }

    sealed class ViewState {
        data class Content(val breeds: List<Breed>) : ViewState()
        object Loading : ViewState()
        object Error : ViewState()
    }
}