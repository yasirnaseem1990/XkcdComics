package com.xkcd.comics.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.xkcd.comics.MainCoroutinesRule
import com.xkcd.comics.MockTestUtil
import com.xkcd.comics.data.DataState
import com.xkcd.comics.data.usercases.FetchXkcdComicsUsecase
import com.xkcd.comics.data.usercases.SearchXkcdComicsUsecase
import com.xkcd.comics.model.Result
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class HomeViewModelTest {

    // Subject under test
    private lateinit var viewModel: HomeViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @MockK
    lateinit var searchPhotosUsecase: SearchXkcdComicsUsecase

    @MockK
    lateinit var fetchComicsUsecase: FetchXkcdComicsUsecase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `test when HomeViewModel is initialized, comics are fetched`() = runBlocking {
        // Given
        val givenPhotos = MockTestUtil.createComics()
        val uiObserver = mockk<Observer<HomeUiState>>(relaxed = true)
        val photosListObserver = mockk<Observer<Result>>(relaxed = true)

        // When
        coEvery { fetchComicsUsecase.invoke() }
            .returns(flowOf(DataState.success(givenPhotos)))

        // Invoke
        viewModel = HomeViewModel(fetchComicsUsecase, searchPhotosUsecase)
        viewModel.uiStateLiveData.observeForever(uiObserver)
        viewModel.comicsListLiveData.observeForever(photosListObserver)

        // Then
        coVerify(exactly = 1) { fetchComicsUsecase.invoke() }
        verify { uiObserver.onChanged(match { it == ContentState }) }
        verify { photosListObserver.onChanged(match { it == givenPhotos }) }
    }
}