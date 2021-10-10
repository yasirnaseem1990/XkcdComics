package com.xkcd.comics.ui.comicdetail

sealed class ComicDetailsUiState
object LoadingState : ComicDetailsUiState()
object ContentState : ComicDetailsUiState()
class ErrorState(val message: String) : ComicDetailsUiState()