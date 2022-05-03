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
    internal fun database(application: Application): RoomDatabase =
        DogDatabaseBuilder(application = application)
            .build()
}