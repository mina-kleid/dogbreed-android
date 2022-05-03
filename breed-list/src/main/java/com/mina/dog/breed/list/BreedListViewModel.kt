package com.mina.dog.breed.list

import androidx.lifecycle.*
import com.mina.dog.breed.common.models.Breed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class BreedListViewModel @Inject constructor(private val breedListRepository: BreedListRepository) :
    ViewModel(),
    DefaultLifecycleObserver {

    private val _viewState: MutableStateFlow<ViewState> = MutableStateFlow(ViewState.Loading)
    val viewState: Flow<ViewState> get() = _viewState


    override fun onCreate(owner: LifecycleOwner) {
        _viewState.value = ViewState.Loading
        viewModelScope.launch {
            _viewState.value = when (val result = breedListRepository.loadBreeds()) {
                is BreedListRepository.BreedListRepositoryResult.Success -> {
                    if (result.breeds.isEmpty()) {
                        ViewState.Empty
                    } else {
                        ViewState.Content(result.breeds)
                    }
                }
                is BreedListRepository.BreedListRepositoryResult.Error -> ViewState.Error
            }
        }
    }

    sealed class ViewState {
        data class Content(val breeds: List<Breed>) : ViewState()
        object Loading : ViewState()
        object Error : ViewState()
        object Empty : ViewState()
    }
}