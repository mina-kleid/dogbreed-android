package com.mina.dog.breed.list

import android.util.Log
import androidx.lifecycle.*
import com.mina.dog.breed.common.models.Breed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
internal class BreedListViewModel @Inject constructor(
    private val breedListRepository: BreedListRepository,
    private val breedImageRepository: BreedImageRepository,
    private val dispatcher: CoroutineDispatcher
) :
    ViewModel(),
    DefaultLifecycleObserver {

    private val _viewState: MutableStateFlow<ViewState> = MutableStateFlow(ViewState.Loading)
    val viewState: Flow<ViewState> get() = _viewState

    private val _viewEvent: Channel<ViewEvent> = Channel(Channel.CONFLATED)
    val viewEvent: Flow<ViewEvent> = _viewEvent.receiveAsFlow()

    override fun onCreate(owner: LifecycleOwner) {
        _viewState.value = ViewState.Loading
        viewModelScope.launch {
            withContext(dispatcher) {
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

    fun breedClicked(breed: Breed) {
        val uriString = "android-app://com.mina.dog/breed"
        viewModelScope.launch {
            _viewEvent.send(ViewEvent.Navigate(uriString = uriString))
        }
    }

    private suspend fun updateImages(breeds: List<Breed>): List<Breed> {
        val breedsWithUpdatedImages: List<Breed> = try {
            breeds
                .filter { it.images.isEmpty() }
                .run { breedImageRepository.images(this) }
        } catch (e: Exception) {
            Log.e("Error", e.localizedMessage)
            emptyList()
        }

        return breeds
            .toMutableSet()
            .apply { this.addAll(breedsWithUpdatedImages) }
            .toList()
    }

    sealed class ViewState {
        data class Content(val breeds: List<Breed>) : ViewState()
        object Loading : ViewState()
        object Error : ViewState()
    }

    sealed class ViewEvent {
        data class Navigate(val uriString: String) : ViewEvent()

    }
}