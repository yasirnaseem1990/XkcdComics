package com.xkcd.comics.ui.comicdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import com.xkcd.comics.base.BaseFragment
import com.xkcd.comics.databinding.FragmentComicDetailBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ComicDetailFragment : BaseFragment<FragmentComicDetailBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentComicDetailBinding
        get() = FragmentComicDetailBinding::inflate


    companion object {}
}