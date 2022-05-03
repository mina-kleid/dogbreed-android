package com.mina.dog.breed.list

import com.mina.dog.breed.common.models.Breed
import com.mina.dog.network.BreedListResponse
import com.mina.dog.network.DogService
import javax.inject.Inject

internal class BreedListRepository @Inject constructor(
    private val dogService: DogService,
    private val converter: BreedConverter
) {

    suspend fun loadBreeds(): BreedListRepositoryResult {
        try {
            val result: Result<BreedListResponse> = dogService.getBreeds()
            if (result.isSuccess) {
                val breedListResponse: BreedListResponse = result.getOrThrow()
                val breedList: List<Breed> = converter.convert(breedListResponse)
                return BreedListRepositoryResult.Success(breedList)
            } else {
                throw Exception()
            }
        } catch (exception: Exception) {
            return BreedListRepositoryResult.Error(exception)
        }
    }


    sealed class BreedListRepositoryResult {

        data class Success(val breeds: List<Breed>) : BreedListRepositoryResult()
        data class Error(val exception: Exception) : BreedListRepositoryResult()
    }
}