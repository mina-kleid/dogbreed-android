package com.mina.dog.breed.list

import com.mina.dog.breed.common.models.Breed
import com.mina.dog.breed.storage.BreedDao
import com.mina.dog.breed.storage.BreedEntity
import com.mina.dog.network.BreedListResponse
import com.mina.dog.network.DogService
import javax.inject.Inject


internal class BreedListRepository @Inject constructor(
    private val dogService: DogService,
    private val responseConverter: BreedResponseConverter,
    private val entityConverter: BreedEntityConverter,
    private val breedDao: BreedDao
) {

    suspend fun loadBreeds(): BreedListRepositoryResult {
        try {
            return if (breedDao.breedCount() > 0) {
                val breedEntities: List<BreedEntity> = breedDao.getAllBreeds()
                val breeds: List<Breed> = breedEntities.map { entityConverter.convert(it) }
                BreedListRepositoryResult.Success(breeds)
            } else {
                val result: Result<BreedListResponse> = dogService.getBreeds()
                if (result.isSuccess) {
                    val breedListResponse: BreedListResponse = result.getOrThrow()
                    val breedList: List<Breed> = responseConverter.convert(breedListResponse)
                    persistBreeds(breedList)
                    BreedListRepositoryResult.Success(breedList)
                } else {
                    throw Exception()
                }
            }
        } catch (exception: Exception) {
            return BreedListRepositoryResult.Error(exception)
        }
    }

    private suspend fun persistBreeds(breeds: List<Breed>) {
        val breedEntities: List<BreedEntity> = breeds.map { entityConverter.convert(it) }
        breedDao.insertAll(breedEntities)
    }

    sealed class BreedListRepositoryResult {

        data class Success(val breeds: List<Breed>) : BreedListRepositoryResult()
        data class Error(val exception: Exception) : BreedListRepositoryResult()
    }
}