package com.xkcd.comics.data.usercases

import com.xkcd.comics.data.repository.XkcdComicsRepository
import javax.inject.Inject

class SearchXkcdComicsUsecase @Inject constructor(private val repository: XkcdComicsRepository) {
    suspend operator fun invoke(
        query: String
    ) = repository.searchXkcdComics(
        query = query
    )
}