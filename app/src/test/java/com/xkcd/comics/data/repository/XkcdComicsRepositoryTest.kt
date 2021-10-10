package com.xkcd.comics.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.xkcd.comics.MainCoroutinesRule
import com.xkcd.comics.MockTestUtil
import com.xkcd.comics.data.DataState
import com.xkcd.comics.data.remote.XkcdComicsApiService
import com.xkcd.comics.data.remote.api.ApiUtil.successCall
import com.xkcd.comics.utils.StringUtils
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class XkcdComicsRepositoryTest {

    // Subject under test
    private lateinit var repository: XkcdComicsRepositoryImpl

    @MockK
    private lateinit var apiService: XkcdComicsApiService

    @MockK
    private lateinit var stringUtils: StringUtils

    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `test loadComics() gives list of comic`() = runBlocking {
        // Given
        repository = XkcdComicsRepositoryImpl(stringUtils, apiService)
        val givenComicsList = MockTestUtil.createComics()
        val apiCall = successCall(givenComicsList)

        // When
        coEvery { apiService.loadXkcdComics() }
            .returns(apiCall)

        // Invoke
        val apiResponseFlow = repository.loadXkcdComics()

        // Then
        MatcherAssert.assertThat(apiResponseFlow, CoreMatchers.notNullValue())

        val comicsListDataState = apiResponseFlow.first()
        MatcherAssert.assertThat(comicsListDataState, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(comicsListDataState, CoreMatchers.instanceOf(DataState.Success::class.java))

        val comicsList = (comicsListDataState as DataState.Success).data
        MatcherAssert.assertThat(comicsList, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(comicsList, CoreMatchers.`is`(givenComicsList))

        coVerify(exactly = 1) { apiService.loadXkcdComics() }
        confirmVerified(apiService)
    }
}