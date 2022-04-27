package com.mina.dog.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BreedImagesResponse(
    @Json(name = "message") val images: List<String>
)
