package com.mina.dog.breed.common.models

public data class Breed(
    val name: String,
    val subBreeds: List<SubBreed>,
    val images: List<String>
) {
    fun imageUrl(): String? = images.firstOrNull()

    override fun equals(other: Any?): Boolean {
        if (other is Breed) {
            return this.name == other.name
        }
        return false
    }

    override fun hashCode(): Int = name.hashCode()
}
