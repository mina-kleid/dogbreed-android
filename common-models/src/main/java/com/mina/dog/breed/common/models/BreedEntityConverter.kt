package com.mina.dog.breed.common.models

import com.mina.dog.breed.storage.BreedEntity
import javax.inject.Inject

public class BreedEntityConverter @Inject constructor() {

    fun convert(breedEntity: BreedEntity): Breed = Breed(
        name = breedEntity.name,
        subBreeds = breedEntity.subBreeds.map { SubBreed(name = it) },
        images = breedEntity.images
    )

    fun convert(breed: Breed): BreedEntity = BreedEntity(
        name = breed.name,
        subBreeds = breed.subBreeds.map { it.name },
        images = breed.images
    )
}
