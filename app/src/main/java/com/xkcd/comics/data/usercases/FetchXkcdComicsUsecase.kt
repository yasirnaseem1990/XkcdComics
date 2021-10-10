package com.xkcd.comics.data.usercases

import com.xkcd.comics.data.repository.XkcdComicsRepository
import com.xkcd.comics.utils.AppConstants.API.COMICS_PER_PAGE
import javax.inject.Inject

class FetchXkcdComicsUsecase @Inject constructor(private val repository: XkcdComicsRepository) {
    suspend operator fun invoke(
        offset: Int = 0,
        limit: Int = COMICS_PER_PAGE
    ) = repository.loadXkcdComics(
        offset = offset,
        limit = limit
    )

}