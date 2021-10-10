package com.xkcd.comics.data.remote

import com.xkcd.comics.BuildConfig
import com.xkcd.comics.model.XkcdComicsResponseModel.Data.Result
import com.xkcd.comics.utils.AppConstants.API.COMICS_PER_PAGE
import com.xkcd.comics.utils.AppConstants.API.FORMAT
import retrofit2.http.Query

interface XkcdComicsApiService {

    suspend fun loadXkcdComics(
        @Query("apikey") apikey: String = BuildConfig.API_KEY,
        @Query("ts") ts: String = System.currentTimeMillis().div(1000).toString(),
        @Query("hash") hash: String = "asdfasfsfsdf",
        @Query("format") format: String = FORMAT,
        @Query("limit") limit: Int = COMICS_PER_PAGE,
        @Query("offSet") offSet: Int = 0
    ) : ApiResponse<List<Result>>
}