package com.xkcd.comics.data.usercases

import com.xkcd.comics.MockTestUtil
import com.xkcd.comics.data.DataState
import com.xkcd.comics.data.repository.XkcdComicsRepository
import com.xkcd.comics.data.repository.XkcdComicsRepositoryTest
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class FetchComicsUsecaseTest {

    @MockK
    private lateinit var repository: XkcdComicsRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `test invoking FetchComicsUsecase gives list of comics`() = runBlocking {
        // Given
        val usecase = FetchXkcdComicsUsecase(repository)
        val givenComics = MockTestUtil.createComics()

        // When
        coEvery { repository.loadXkcdComics() }
            .returns(flowOf(DataState.success(givenComics)))

        // Invoke
        val comicsListFlow = usecase()

        // Then
        MatcherAssert.assertThat(comicsListFlow, CoreMatchers.notNullValue())

        val comicsListDataState = comicsListFlow.first()
        MatcherAssert.assertThat(comicsListDataState, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(comicsListDataState, CoreMatchers.instanceOf(DataState.Success::class.java))

        val comicsList = (comicsListDataState as DataState.Success).data
        MatcherAssert.assertThat(comicsList, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(comicsList, `is`(givenComics))
    }
}