package com.xkcd.comics.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.xkcd.comics.R
import com.xkcd.comics.adapters.ComicsAdapter
import com.xkcd.comics.base.BaseFragment
import com.xkcd.comics.databinding.FragmentHomeBinding
import com.xkcd.comics.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {


    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var comicsAdapter: ComicsAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupViews()
        initObservations()
    }


    private fun setupViews() {
        context?.let { ctx ->

            // Comics RecyclerView
            comicsAdapter = ComicsAdapter() { comics, _ ->

            }
            comicsAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            bi.recyclerComics.adapter = comicsAdapter

            // NestedScrollView
            bi.nestedScrollView.setOnScrollChangeListener { v: NestedScrollView, _, scrollY, _, _ ->
                if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                    // Need to call the method for load more comics
                }
            }

            // Input Text Search
            bi.inputSearchComics.setEndIconOnClickListener {
                bi.txtSearchComics.setText("")
                viewModel.fetchComics(0)
            }

            bi.txtSearchComics.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    bi.txtSearchComics.dismissKeyboard()
                    // Need to call the method for perform search
                    true
                }
                false
            }
        }
    }


    private fun initObservations() {
        viewModel.uiStateLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is LoadingState -> {
                    bi.recyclerComics.gone()
                    bi.progressComics.visible()
                }

                is LoadingNextPageState -> {
                    bi.progressComics.gone()
                    showToast(getString(R.string.message_load_comics_str))
                }

                is ContentState -> {
                    bi.recyclerComics.visible()
                    bi.progressComics.gone()
                }

                is ErrorState -> {
                    bi.progressComics.gone()
                    bi.nestedScrollView.showSnack(state.message, getString(R.string.action_retry_str)) {
                        /*viewModel.retry()*/
                    }
                }

                is ErrorNextPageState -> {
                    bi.nestedScrollView.showSnack(state.message, getString(R.string.action_retry_str)) {
                       /* viewModel.retry()*/
                    }
                }
            }
        }

        viewModel.comicsListLiveData.observe(viewLifecycleOwner) { comics ->
            comicsAdapter.updateItems(comics)
        }
    }
}