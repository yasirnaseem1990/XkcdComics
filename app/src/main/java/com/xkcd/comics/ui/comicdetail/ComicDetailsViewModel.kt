package com.xkcd.comics.ui.comicdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.xkcd.comics.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ComicDetailsViewModel @Inject constructor() : ViewModel(){

    private var _uiState = MutableLiveData<ComicDetailsUiState>()
    var uiStateLiveData: LiveData<ComicDetailsUiState> = _uiState

    private var _comicModel = MutableLiveData<Result>()
    var comicModelLiveData: LiveData<Result> = _comicModel

    fun initPhotoModel(photo: Result) {
        _comicModel.value = photo
    }
}