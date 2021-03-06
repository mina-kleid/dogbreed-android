package com.mina.dog.breed

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mina.dog.breed.common.models.Breed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class BreedViewModel @Inject constructor(
    private val repository: BreedRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _viewState: MutableStateFlow<ViewState> = MutableStateFlow(ViewState.Loading)
    val viewState: Flow<ViewState> get() = _viewState

    init {
        try {
            val breedName: String = savedStateHandle
                .get("breed_name") ?: throw NullPointerException("Breed name argument not found")
            viewModelScope.launch {
                val breed = repository.getBreed(breedName)
                _viewState.value = content(breed)
            }
        } catch (e: Exception) {
            _viewState.value = ViewState.Error
        }
    }

    private fun content(breed: Breed): ViewState.Content = ViewState.Content(
        title = breed.name,
        subBreeds = if (breed.subBreeds.isEmpty()) null else breed.subBreeds.joinToString(separator = "\n") { it.name },
        images = breed.images
    )

    sealed class ViewState {
        data class Content(val title: String, val subBreeds: String?, val images: List<String>) :
            ViewState()

        object Loading : ViewState()
        object Error : ViewState()
    }

}