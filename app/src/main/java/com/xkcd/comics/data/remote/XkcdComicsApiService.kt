package com.xkcd.comics.data.remote

import com.xkcd.comics.model.Result
import retrofit2.http.GET

interface XkcdComicsApiService {

    @GET("info.0.json")
    suspend fun loadXkcdComics() : ApiResponse<Result>
}