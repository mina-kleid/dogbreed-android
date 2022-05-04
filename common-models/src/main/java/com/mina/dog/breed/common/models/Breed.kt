package com.mina.dog.breed.common.models

public data class Breed(
    val name: String,
    val subBreeds: List<SubBreed>,
    val images: List<String> = emptyList()
) {
    fun imageUrl(): String? = images.firstOrNull()
}
