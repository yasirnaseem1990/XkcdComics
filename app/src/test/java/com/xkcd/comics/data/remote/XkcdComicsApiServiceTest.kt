package com.xkcd.comics.data.remote

import com.xkcd.comics.MainCoroutinesRule
import com.xkcd.comics.data.remote.api.ApiAbstract
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class XkcdComicsApiServiceTest : ApiAbstract<XkcdComicsApiService>() {

    private lateinit var apiService: XkcdComicsApiService

    @get:Rule
    var coroutineRule = MainCoroutinesRule()

    @Before
    fun setUp() {
        apiService = createService(XkcdComicsApiService::class.java)
    }

    @After
    fun tearDown() {
    }

    @Throws(IOException::class)
    @Test
    fun `test loadComics() returns list of Comics`() = runBlocking {
        // Given
        enqueueResponse("/comics_list_response.json")

        // Invoke
        val response = apiService.loadXkcdComics()
        val responseBody = requireNotNull((response as ApiResponse.ApiSuccessResponse).data)
        mockWebServer.takeRequest()

        // Then
        MatcherAssert.assertThat(responseBody.safe_title, CoreMatchers.`is`("Woodpecker"))
        MatcherAssert.assertThat(responseBody.title, CoreMatchers.`is`("Woodpecker"))
        MatcherAssert.assertThat(responseBody.num, CoreMatchers.`is`(614))
        MatcherAssert.assertThat(responseBody.year, CoreMatchers.`is`("2009"))
        MatcherAssert.assertThat(responseBody.img, CoreMatchers.`is`("https://imgs.xkcd.com/comics/woodpecker.png"))
        MatcherAssert.assertThat(responseBody.transcript, CoreMatchers.`is`("[[A man with a beret and a woman are standing on a boardwalk, leaning on a handrail.]] Man: A woodpecker! <<Pop pop pop>> Woman: Yup. [[The woodpecker is banging its head against a tree.]] Woman: He hatched about this time last year. <<Pop pop pop pop>> [[The woman walks away. The man is still standing at the handrail.]] Man: ... woodpecker? Man: It's your birthday! Man: Did you know? Man: Did... did nobody tell you? [[The man stands, looking.]] [[The man walks away.]] [[There is a tree.]] [[The man approaches the tree with a present in a box, tied up with ribbon.]] [[The man sets the present down at the base of the tree and looks up.]] [[The man walks away.]] [[The present is sitting at the bottom of the tree.]] [[The woodpecker looks down at the present.]] [[The woodpecker sits on the present.]] [[The woodpecker pulls on the ribbon tying the present closed.]] ((full width panel)) [[The woodpecker is flying, with an electric drill dangling from its feet, held by the cord.]] {{Title text: If you don't have an extension cord I can get that too. Because we're friends! Right?}}"))
    }
}