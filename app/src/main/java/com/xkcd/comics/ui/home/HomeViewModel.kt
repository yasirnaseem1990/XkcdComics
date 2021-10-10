package com.xkcd.comics.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xkcd.comics.data.DataState
import com.xkcd.comics.data.usercases.FetchXkcdComicsUsecase
import com.xkcd.comics.model.XkcdComicsResponseModel.Data.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchComicsUserCase: FetchXkcdComicsUsecase
) : ViewModel() {

    private var _uiState = MutableLiveData<HomeUiState>()
    var uiStateLiveData: LiveData<HomeUiState> = _uiState

    private var _comicsList = MutableLiveData<List<Result>>()
    var comicsListLiveData: LiveData<List<Result>> = _comicsList

    private var pageNumber = 0

    init {
        fetchComics(pageNumber)
    }


    fun fetchComics(page: Int) {
        _uiState.postValue(if (page == 1) LoadingState else LoadingNextPageState)
        viewModelScope.launch {
            fetchComicsUserCase(page).collect { dataState ->
                when (dataState) {
                    is DataState.Success -> {
                        if (page == 0) {
                            // First page
                            _uiState.postValue(ContentState)
                            _comicsList.postValue(dataState.data)
                        }else {
                            // Any other page
                            _uiState.postValue(ContentNextPageState)
                            val currentList = arrayListOf<Result>()
                            _comicsList.value?.let { currentList.addAll(it) }
                            currentList.addAll(dataState.data)
                            _comicsList.postValue(currentList)
                        }
                    }
                    is DataState.Error -> {
                        if (page == 0) {
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