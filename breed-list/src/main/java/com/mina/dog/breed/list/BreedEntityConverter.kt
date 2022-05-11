package com.mina.dog.breed.list

import com.mina.dog.breed.common.models.Breed
import com.mina.dog.breed.common.models.SubBreed
import com.mina.dog.breed.storage.BreedEntity
import javax.inject.Inject

internal class BreedEntityConverter @Inject constructor() {

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
