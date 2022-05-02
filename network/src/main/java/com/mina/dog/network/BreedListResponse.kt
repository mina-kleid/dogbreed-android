package com.mina.dog.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BreedListResponse(
    @Json(name = "message") val breeds: Map<String, List<String>>
)
