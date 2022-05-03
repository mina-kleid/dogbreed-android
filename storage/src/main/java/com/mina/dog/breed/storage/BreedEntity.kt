package com.mina.dog.breed.storage

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["name"], tableName = "Breed")
internal data class BreedEntity(
    val name: String,
    @ColumnInfo(defaultValue = "false") val isFavorite: Boolean = false,
)
