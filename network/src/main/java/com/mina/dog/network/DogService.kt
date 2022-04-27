package com.mina.dog.network

import retrofit2.Response
import retrofit2.Retrofit
import java.lang.Exception
import javax.inject.Inject

public class DogService @Inject constructor(
    private val retrofit: Retrofit
) {

    private val dogApi = retrofit.create(DogApi::class.java)

    suspend fun getBreeds(): Result<BreedListResponse> = try {
        val response: Response<BreedListResponse> = dogApi.breeds()
        val body: BreedListResponse? = response.body()
        if (response.isSuccessful && body != null) {
            Result.success(body)
        } else {
            throw DogApiException("Unsuccessful response")
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun getBreedImages(breed: String): Result<BreedImagesResponse> = try {
        val response: Response<BreedImagesResponse> = dogApi.breedImages(breed = breed)
        val body: BreedImagesResponse? = response.body()
        if (response.isSuccessful && body != null) {
            Result.success(body)
        } else {
            throw DogApiException("Unsuccessful response")
        }
    } catch (e: Exception) {
        Result.failure(e)
    }
}