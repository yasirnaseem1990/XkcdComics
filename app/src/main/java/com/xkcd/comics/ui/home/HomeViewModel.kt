package com.xkcd.comics.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xkcd.comics.data.DataState
import com.xkcd.comics.data.usercases.FetchXkcdComicsUsecase
import com.xkcd.comics.data.usercases.SearchXkcdComicsUsecase
import com.xkcd.comics.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchComicsUserCase: FetchXkcdComicsUsecase,
    private val searchComicsUsecase: SearchXkcdComicsUsecase
) : ViewModel() {

    private var _uiState = MutableLiveData<HomeUiState>()
    var uiStateLiveData: LiveData<HomeUiState> = _uiState

    private var _comicsList = MutableLiveData<Result>()
    var comicsListLiveData: LiveData<Result> = _comicsList

    private var pageNumber = 1
    private var searchQuery: String = ""

    init {
        fetchComics(pageNumber)
    }

    fun fetchComics(page: Int) {
        _uiState.postValue(if (page == 1) LoadingState else LoadingNextPageState)
        viewModelScope.launch {
            fetchComicsUserCase().collect { dataState ->
                when (dataState) {
                    is DataState.Success -> {
                        if (page == 1) {
                            // First page
                            _uiState.postValue(ContentState)
                            _comicsList.postValue(dataState.data)
                        } else {
                            // Any other page
                            _uiState.postValue(ContentNextPageState)
                            _comicsList.postValue(dataState.data)
                        }
                    }
                    is DataState.Error -> {
                        if (page == 1) {
                            _uiState.postValue(ErrorState(dataState.message))
                        } else {
                            _uiState.postValue(ErrorNextPageState(dataState.message))
                        }
                    }
                }
            }
        }
    }

    fun loadMoreComics() {
        pageNumber++
        if (searchQuery == "")
            fetchComics(pageNumber)
        else
            searchComics(searchQuery, pageNumber)
    }

    fun retry() {
        if (searchQuery == "")
            fetchComics(pageNumber)
        else
            searchComics(searchQuery, pageNumber)
    }

    fun searchComics(query: String) {
        searchQuery = query
        pageNumber = 1
        searchComics(query, pageNumber)
    }

    private fun searchComics(query: String, page: Int) {
        _uiState.postValue(if (page == 1) LoadingState else LoadingNextPageState)
        viewModelScope.launch {
            searchComicsUsecase(query).collect { dataState ->
                when (dataState) {
                    is DataState.Success -> {
                        if (page == 1) {
                            // First page
                            _uiState.postValue(ContentState)
                            _comicsList.postValue(dataState.data)
                        } else {
                            // Any other page
                            _uiState.postValue(ContentNextPageState)
                            _comicsList.postValue(dataState.data)
                        }
                    }

                    is DataState.Error -> {
                        if (page == 1) {
                            _uiState.postValue(ErrorState(dataState.message))
                        } else {
                            _uiState.postValue(ErrorNextPageState(dataState.message))
                        }
                    }
                }
            }
        }
    }
}