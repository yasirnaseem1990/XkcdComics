package com.xkcd.comics.data.repository

import com.xkcd.comics.data.DataState
import com.xkcd.comics.model.XkcdComicsResponseModel.Data.Result
import kotlinx.coroutines.flow.Flow


/**
 * ImagineRepository is an interface data layer to handle communication with any data source such as Server or local database.
 * @see [XkcdComicsRepositoryImpl] for implementation of this class to utilize XkcdComics API.
 * @author yasirnaseem1990@gmail.com
 */
interface XkcdComicsRepository {
    suspend fun loadXkcdComics(offset: Int, limit: Int): Flow<DataState<List<Result>>>
    suspend fun searchXkcdComics(offset: Int, limit: Int, query: String): Flow<DataState<List<Result>>>
}