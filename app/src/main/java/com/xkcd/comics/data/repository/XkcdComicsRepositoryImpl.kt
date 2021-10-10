package com.xkcd.comics.data.repository

import com.xkcd.comics.data.DataState
import com.xkcd.comics.data.remote.*
import com.xkcd.comics.model.Result
import com.xkcd.comics.utils.StringUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject


/**
 * This is an implementation of [XkcdComicsRepository] to handle communication with [XkcdComicsApiService] server.
 * @author yasirnaseem1990@gmail.com
 */
class XkcdComicsRepositoryImpl @Inject constructor(
    private val stringUtils: StringUtils,
    private val apiService: XkcdComicsApiService
) : XkcdComicsRepository {
    override suspend fun loadXkcdComics(): Flow<DataState<Result>> {
        return flow {
            apiService.loadXkcdComics().apply {
                this.onSuccessSuspend {
                    data?.let {
                        emit(DataState.success(it))
                    }
                }
                // handle the case when the API request gets an error response.
                // e.g. internal server error.
            }.onErrorSuspend {
                emit(DataState.error<Result>(message()))

                // handle the case when the API request gets an exception response.
                // e.g. network connection error.
            }.onExceptionSuspend {
                if (this.exception is IOException) {
                    emit(DataState.error<Result>(stringUtils.noNetworkErrorMessage()))
                } else {
                    emit(DataState.error<Result>(stringUtils.somethingWentWrong()))
                }
            }
        }
    }

    override suspend fun searchXkcdComics(
        query: String
    ): Flow<DataState<Result>> {
        return flow {

        }
    }

}