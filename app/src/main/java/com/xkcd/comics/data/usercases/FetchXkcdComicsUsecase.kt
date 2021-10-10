package com.xkcd.comics.data.usercases

import com.xkcd.comics.data.repository.XkcdComicsRepository
import javax.inject.Inject

class FetchXkcdComicsUsecase @Inject constructor(private val repository: XkcdComicsRepository) {
    suspend operator fun invoke() = repository.loadXkcdComics()

}