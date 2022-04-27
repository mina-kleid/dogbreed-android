package com.mina.dog.network

data class DogApiException(override val message: String?) : Exception()