package com.mina.dog.breed.storage

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Breed")
data class BreedEntity(
    @PrimaryKey val name: String,
    val subBreeds: List<String>,
    val images: List<String>,
    @ColumnInfo(defaultValue = "false") val isFavorite: Boolean = false,
)
