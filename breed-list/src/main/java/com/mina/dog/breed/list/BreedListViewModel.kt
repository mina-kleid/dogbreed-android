package com.mina.dog.breed.list

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
                        viewState(result.breeds)
                    }
                    is BreedListRepository.BreedListRepositoryResult.Error -> ViewState.Error
                }
            }
        }
    }

    private suspend fun viewState(breeds: List<Breed>): ViewState {
        val breedListWithImages = breedImageRepository.images(breeds)
        return ViewState.Content(breedListWithImages)
    }

    sealed class ViewState {
        data class Content(val breeds: List<Breed>) : ViewState()
        object Loading : ViewState()
        object Error : ViewState()
    }
}