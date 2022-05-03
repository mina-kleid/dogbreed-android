package com.mina.dog.breed.list

import com.mina.dog.breed.common.models.Breed
import com.mina.dog.breed.common.models.SubBreed
import com.mina.dog.network.BreedListResponse
import javax.inject.Inject

internal class BreedConverter @Inject constructor() {

    fun convert(response: BreedListResponse): List<Breed> = response
        .breeds
        .toList()
        .map { breedPair ->
            val subBreeds: List<SubBreed> = breedPair.second.map { SubBreed(name = it) }
            Breed(name = breedPair.first, subBreeds = subBreeds)
        }
    }
