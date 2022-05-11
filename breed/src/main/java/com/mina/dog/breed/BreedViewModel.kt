package com.mina.dog.breed

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class BreedViewModel @Inject constructor() : ViewModel() {

    fun initialize(breedName: String) {
    }

}