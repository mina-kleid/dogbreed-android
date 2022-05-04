package com.mina.dog.breed.storage

import android.app.Application
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
public object DogStorageModule {

    @Provides
    @Reusable
    fun breedDao(application: Application): BreedDao =
        DogDatabaseBuilder(application = application)
            .build()
            .breedDao()
}