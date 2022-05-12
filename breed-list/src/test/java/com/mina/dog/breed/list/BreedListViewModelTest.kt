package com.mina.dog.breed.list

import app.cash.turbine.test
import com.mina.dog.breed.common.models.Breed
import com.nhaarman.mockitokotlin2.any
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
    fun `should update breeds list with images from image repository`() = runBlockingTest {
        val breedWithoutImage = Breed("test1", emptyList(), emptyList())
        val breedWithImage = Breed("test2", emptyList(), listOf("image2"))
        val breedList: List<Breed> = listOf(breedWithImage, breedWithoutImage)

        given(listRepository.loadBreeds())
            .willReturn(BreedListRepository.BreedListRepositoryResult.Success(breedList))
        given(imageRepository.images(listOf(breedWithoutImage))).willReturn(
            listOf(breedWithoutImage.copy(images = listOf("image1")))
        )

        viewModel.onCreate(mock())

        viewModel
            .viewState
            .test {
                val viewState = expectMostRecentItem()
                assertEquals(
                    viewState,
                    BreedListViewModel.ViewState.Content(
                        listOf(breedWithImage, breedWithoutImage)
                    )
                )
                val breeds: List<Breed> = (viewState as BreedListViewModel.ViewState.Content).breeds
                assertEquals(breeds[1].images, listOf("image1"))
            }
    }
}