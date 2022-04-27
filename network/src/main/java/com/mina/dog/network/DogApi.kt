package com.mina.dog.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

internal interface DogApi {
    @GET("breeds/list/all")
    suspend fun breeds(): Response<BreedListResponse>

    @GET("breed/{breed}/images")
    suspend fun breedImages(@Path("breed") breed: String): Response<BreedImagesResponse>
}