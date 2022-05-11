package com.mina.dog.breed

import com.mina.dog.breed.common.models.Breed
import com.mina.dog.breed.common.models.BreedEntityConverter
import com.mina.dog.breed.storage.BreedDao
import com.mina.dog.breed.storage.BreedEntity
import javax.inject.Inject

internal class BreedRepository @Inject constructor(
    private val breedDao: BreedDao,
    private val converter: BreedEntityConverter
) {

    suspend fun getBreed(breedName: String): Breed {
        val breedEntity: BreedEntity = breedDao.getBreed(breedName) ?: throw Exception()

        return converter.convert(breedEntity)
    }
}