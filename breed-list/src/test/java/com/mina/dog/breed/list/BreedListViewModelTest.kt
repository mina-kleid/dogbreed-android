package com.mina.dog.breed.list

import app.cash.turbine.test
import com.mina.dog.breed.common.models.Breed
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
internal class BreedListViewModelTest {

    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    private val listRepository: BreedListRepository = mock()
    private val imageRepository: BreedImageRepository = mock()

    private val viewModel = BreedListViewModel(
        breedListRepository = listRepository,
        breedImageRepository = imageRepository,
        dispatcher = TestCoroutineDispatcher()
    )


    @Test
    fun `viewState should emit loading state on start`() = runBlockingTest {
        viewModel
            .viewState
            .test {
                assertEquals(expectMostRecentItem(), BreedListViewModel.ViewState.Loading)
            }
    }

    @Test
    fun `viewState should emit error when list repository returns error`() = runBlockingTest {
        given(listRepository.loadBreeds())
            .willReturn(BreedListRepository.BreedListRepositoryResult.Error(Exception()))

        viewModel.onCreate(mock())

        viewModel
            .viewState
            .test {
                assertEquals(expectMostRecentItem(), BreedListViewModel.ViewState.Error)
            }
    }

    @Test
    fun `viewState should emit content`() = runBlockingTest {
        val breed = Breed("test", emptyList(), emptyList())
        val breedList: List<Breed> = listOf(breed)
        given(listRepository.loadBreeds())
            .willReturn(BreedListRepository.BreedListRepositoryResult.Success(breedList))
        given(imageRepository.images(breedList)).willReturn(breedList)

        viewModel.onCreate(mock())

        viewModel
            .viewState
            .test {
                assertEquals(
                    expectMostRecentItem(),
                    BreedListViewModel.ViewState.Content(breedList)
                )
            }
    }

    @Test
    fun `viewState should emit content when image repository throws exception`() = runBlockingTest {
        val breed = Breed("test", emptyList(), emptyList())
        val breedList: List<Breed> = listOf(breed)
        given(listRepository.loadBreeds())
            .willReturn(BreedListRepository.BreedListRepositoryResult.Success(breedList))
        given(imageRepository.images(breedList)).willAnswer { throw Exception() }

        viewModel.onCreate(mock())

        viewModel
            .viewState
            .test {
                assertEquals(
                    expectMostRecentItem(),
                    BreedListViewModel.ViewState.Content(breedList)
                )
            }
    }
}