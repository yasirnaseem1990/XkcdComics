package com.xkcd.comics.data.remote

import com.xkcd.comics.model.Result
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface XkcdComicsApiService {

    @GET("info.0.json")
    suspend fun loadXkcdComics() : ApiResponse<Result>

    @GET("{num}/info.0.json")
    suspend fun searchPhotos(
        @Path("num") num: Int,
        @Query("query") query: String
    ): ApiResponse<Result>
}