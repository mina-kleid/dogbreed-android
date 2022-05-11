package com.mina.dog.breed.list

import com.mina.dog.breed.common.models.Breed
import com.mina.dog.breed.common.models.BreedEntityConverter
import com.mina.dog.breed.storage.BreedDao
import com.mina.dog.breed.storage.BreedEntity
import com.mina.dog.network.BreedListResponse
import com.mina.dog.network.DogService
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
internal class BreedListRepositoryTest {

    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    private val dogService: DogService = mock()
    private val responseConverter: BreedResponseConverter = mock()
    private val entityConverter: BreedEntityConverter = mock()
    private val breedDao: BreedDao = mock()

    private val breed = Breed("test", emptyList(), emptyList())
    private val breedEntity = BreedEntity("test", emptyList(), emptyList())
    private val breedListResponse = mock<BreedListResponse>()

    private val repository = BreedListRepository(
        dogService = dogService,
        breedDao = breedDao,
        entityConverter = entityConverter,
        responseConverter = responseConverter
    )

    @Test
    fun `should return breeds from database if they exist`() = runBlockingTest {
        given(breedDao.breedCount()).willReturn(1)
        given(breedDao.getAllBreeds()).willReturn(listOf(breedEntity))
        given(entityConverter.convert(breedEntity)).willReturn(breed)


        val result = repository.loadBreeds()
        Assert.assertEquals(
            result,
            BreedListRepository.BreedListRepositoryResult.Success(listOf(breed))
        )
    }

    @Test
    fun `should call get breeds if no data exists in database`() = runBlockingTest {
        given(breedDao.breedCount()).willReturn(0)

        repository.loadBreeds()

        verify(dogService).getBreeds()
    }

    @Test
    fun `should persist breeds in database if fetched from service`() = runBlockingTest {
        val breedListResult = Result.success(breedListResponse)

        given(breedDao.breedCount()).willReturn(0)
        given(dogService.getBreeds()).willReturn(breedListResult)
        given(responseConverter.convert(breedListResponse)).willReturn(listOf(breed))
        given(entityConverter.convert(breed)).willReturn(breedEntity)

        repository.loadBreeds()

        verify(breedDao).insertAll(listOf(breedEntity))
    }

    @Test
    fun `should return Success with breeds from service`() = runBlockingTest {
        val breedListResult = Result.success(breedListResponse)

        given(breedDao.breedCount()).willReturn(0)
        given(dogService.getBreeds()).willReturn(breedListResult)
        given(responseConverter.convert(breedListResponse)).willReturn(listOf(breed))
        given(entityConverter.convert(breed)).willReturn(breedEntity)

        val result = repository.loadBreeds()

        Assert.assertEquals(
            result,
            BreedListRepository.BreedListRepositoryResult.Success(listOf(breed))
        )
    }

    @Test
    fun `should return Error when breed service is failure`() = runBlockingTest {
        val exception = Exception()

        given(breedDao.breedCount()).willReturn(0)
        given(dogService.getBreeds()).willAnswer{ throw exception }

        val result = repository.loadBreeds()

        Assert.assertEquals(
            result,
            BreedListRepository.BreedListRepositoryResult.Error(exception)
        )

    }
}