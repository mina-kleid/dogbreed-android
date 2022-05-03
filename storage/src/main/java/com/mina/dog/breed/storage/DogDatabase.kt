package com.mina.dog.breed.storage

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [BreedEntity::class],
    version = 1
)
internal abstract class DogDatabase : RoomDatabase() {
    abstract fun breedDao(): BreedDao
}