package com.mina.dog.breed.list

import com.mina.dog.network.DogService
import javax.inject.Inject

internal class BreedListRepository @Inject constructor(dogService: DogService){

    suspend fun loadBreeds() {

    }
}