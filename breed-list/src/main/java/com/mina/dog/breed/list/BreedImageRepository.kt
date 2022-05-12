package com.mina.dog.breed.list

import android.util.Log
import com.mina.dog.breed.common.models.Breed
import com.mina.dog.breed.common.models.BreedEntityConverter
import com.mina.dog.breed.storage.BreedDao
import com.mina.dog.breed.storage.BreedEntity
import com.mina.dog.network.DogService
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

internal class BreedImageRepository @Inject constructor(
    private val dogService: DogService,
    private val breedDao: BreedDao,
    private val entityConverter: BreedEntityConverter
) {

    suspend fun images(breedList: List<Breed>): List<Breed> {
        return supervisorScope {
            val deferred = breedList
                .map { breed ->
                    async {
                        return@async Pair(breed, dogService.getBreedImages(breed.name))
                    }
                }
            try {
                deferred
                    .awaitAll()
                    .filter { it.second.isSuccess }
                    .map {
                        it.first.copy(images = it.second.getOrThrow().images)
                    }
                    .apply { updateBreeds(this) }
            } catch (e: Exception) {
                Log.e("Error", e.localizedMessage)
                emptyList()
            }
        }
    }

    private suspend fun updateBreeds(breeds: List<Breed>) {
        val breedEntities: List<BreedEntity> = breeds.map { entityConverter.convert(it) }
        breedDao.updateAll(breedEntities)
    }
}