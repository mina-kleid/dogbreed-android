package com.mina.dog.breed.list

import com.mina.dog.breed.common.models.Breed
import com.mina.dog.breed.common.models.BreedEntityConverter
import com.mina.dog.breed.storage.BreedDao
import com.mina.dog.breed.storage.BreedEntity
import com.mina.dog.network.BreedImagesResponse
import com.mina.dog.network.DogService
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
internal class BreedImageRepositoryTest {

    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    private val dogService: DogService = mock()
    private val breedDao: BreedDao = mock()
    private val entityConverter: BreedEntityConverter = mock()
    private val breed = Breed("breed1", emptyList(), emptyList())

    private val repository = BreedImageRepository(dogService, breedDao, entityConverter)

    @Test
    fun `should return breeds with images from service`() = runBlockingTest {
        val breedImageResponse = BreedImagesResponse(listOf("image1", "image2"))
        val breedImageResult: Result<BreedImagesResponse> = Result.success(breedImageResponse)

        given(dogService.getBreedImages(any())).willReturn(breedImageResult)

        val result = repository.images(listOf(breed))

        assertEquals(result, listOf(breed.copy(images = listOf("image1", "image2"))))
    }

    @Test
    fun `should update database after fetching from service`() = runBlockingTest {
        val breedImageResponse = BreedImagesResponse(listOf("image1", "image2"))
        val breedImageResult: Result<BreedImagesResponse> = Result.success(breedImageResponse)
        val breedEntity = BreedEntity("breed1", emptyList(), emptyList())
        given(dogService.getBreedImages(any())).willReturn(breedImageResult)
        given(entityConverter.convert(breed)).willReturn(breedEntity)

        repository.images(listOf(breed))

        verify(breedDao).updateAll(listOf(breedEntity))
    }

    @Test
    fun `should return empty list if exception`() = runBlockingTest {
        given(dogService.getBreedImages(any())).willAnswer { throw Exception()}

        val result = repository.images(listOf(breed))

        assertEquals(result, emptyList<Breed>())
    }

}