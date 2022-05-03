package com.mina.dog.breed.storage


import android.app.Application
import androidx.room.Room
import javax.inject.Inject

internal class DogDatabaseBuilder @Inject constructor(private val application: Application) {

    fun build(): DogDatabase = Room.databaseBuilder(
            application,
            DogDatabase::class.java, "dog-db"
        ).build()
}