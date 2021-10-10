package com.xkcd.comics.ui.comicdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.xkcd.comics.base.BaseFragment
import com.xkcd.comics.databinding.FragmentComicDetailBinding
import com.xkcd.comics.model.Result
import androidx.fragment.app.viewModels
import coil.load

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComicDetailFragment : BaseFragment<FragmentComicDetailBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentComicDetailBinding
        get() = FragmentComicDetailBinding::inflate

    private val viewModel: ComicDetailsViewModel by viewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val comicDetail = arguments?.getParcelable<Result>("comics")
        if (comicDetail == null) {
            findNavController().popBackStack()
            return
        }

        setupViews()
        initObservations()

        viewModel.initPhotoModel(comicDetail)
    }

    private fun setupViews() {
    }

    private fun initObservations() {
        viewModel.comicModelLiveData.observe(viewLifecycleOwner) { comicDetail ->
            bi.tvComicTitle.text = comicDetail.safe_title
            bi.tvComicDetail.text = comicDetail.transcript
            bi.imgComics.load(comicDetail.img)
        }
    }
}