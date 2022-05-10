package com.mina.dog.breed.list

import app.cash.turbine.test
import com.mina.dog.breed.common.models.Breed
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
internal class BreedListViewModelTest {

    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    private val repository: BreedListRepository = mock()

    private val viewModel = BreedListViewModel(breedListRepository = repository)


    @Test
    fun `viewState should emit loading state on start`() = runBlockingTest {
        viewModel
            .viewState
            .test {
                assertEquals(expectMostRecentItem(), BreedListViewModel.ViewState.Loading)
            }
    }
    
    @Test
    fun `viewState should emit error when repository returns error`() = runBlockingTest {
        given(repository.loadBreeds())
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
        val breedList: List<Breed> = mock()
        given(repository.loadBreeds())
            .willReturn(BreedListRepository.BreedListRepositoryResult.Success(breedList))

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