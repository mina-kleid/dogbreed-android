package com.example.breed.list

import androidx.lifecycle.*
import com.mina.dog.network.DogService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class BreedListViewModel @Inject constructor(private val breedListRepository: BreedListRepository) :
    ViewModel(),
    LifecycleObserver {

    private val _viewState: MutableStateFlow<ViewState> = MutableStateFlow(ViewState.Loading)
    val viewState: Flow<ViewState> get() = _viewState

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun loadBreeds() {
        _viewState.value = ViewState.Loading
        viewModelScope.launch {
            breedListRepository.loadBreeds()
        }
    }

    sealed class ViewState {
        object Content : ViewState()
        object Empty : ViewState()
        object Loading : ViewState()
        object Error : ViewState()
    }
}