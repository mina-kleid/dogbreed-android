package com.mina.dog.breed.list

import com.mina.dog.breed.common.models.Breed
import com.mina.dog.breed.storage.BreedDao
import com.mina.dog.breed.storage.BreedEntity
import com.mina.dog.network.BreedListResponse
import com.mina.dog.network.DogService
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject


internal class BreedListRepository @Inject constructor(
    private val dogService: DogService,
    private val converter: BreedConverter,
    private val breedDao: BreedDao
) {

    suspend fun loadBreeds(): BreedListRepositoryResult {
        try {
            return if (breedDao.breedCount() > 0) {
                val breedEntities: List<BreedEntity> = breedDao.getAllBreeds()
                val breeds: List<Breed> = breedEntities.map { converter.convert(it) }
                BreedListRepositoryResult.Success(breeds)
            } else {
                val result: Result<BreedListResponse> = dogService.getBreeds()
                if (result.isSuccess) {
                    val breedListResponse: BreedListResponse = result.getOrThrow()
                    val breedList: List<Breed> = converter.convert(breedListResponse)
                    val breedListWithImages: List<Breed> = images(breedList)
                    persistBreeds(breedListWithImages)
                    BreedListRepositoryResult.Success(breedListWithImages)
                } else {
                    throw Exception()
                }
            }
        } catch (exception: Exception) {
            return BreedListRepositoryResult.Error(exception)
        }
    }

    private suspend fun images(breedList: List<Breed>): List<Breed> {
        return supervisorScope {
            val deferred = breedList.map { breed ->
                async {
                    return@async Pair(breed, dogService.getBreedImages(breed.name))
                }
            }

            deferred
                .awaitAll()
                .map {
                    it.first.copy(images = it.second.getOrThrow().images)
                }
        }
    }

    private suspend fun persistBreeds(breeds: List<Breed>) {
        val breedEntities: List<BreedEntity> = breeds.map { converter.convert(it) }
        breedDao.insertAll(breedEntities)
    }

    sealed class BreedListRepositoryResult {

        data class Success(val breeds: List<Breed>) : BreedListRepositoryResult()
        data class Error(val exception: Exception) : BreedListRepositoryResult()
    }
}