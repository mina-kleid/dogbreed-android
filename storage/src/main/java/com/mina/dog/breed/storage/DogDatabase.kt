package com.mina.dog.breed.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [BreedEntity::class],
    version = 1
)
@TypeConverters(JsonConverter::class)
internal abstract class DogDatabase : RoomDatabase() {
    abstract fun breedDao(): BreedDao
}