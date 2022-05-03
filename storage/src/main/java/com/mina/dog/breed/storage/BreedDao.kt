package com.mina.dog.breed.storage

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import kotlinx.coroutines.flow.Flow

@Dao
internal interface BreedDao {
    @Query("SELECT * FROM breed where isFavorite = 1")
    fun getFavoriteBreeds(): Flow<List<BreedEntity>>

    @Query("SELECT * FROM breed where name = :name LIMIT 1")
    suspend fun getBreed(name: String): BreedEntity?

    @Insert(onConflict = REPLACE)
    suspend fun insert(breedEntity: BreedEntity)
}