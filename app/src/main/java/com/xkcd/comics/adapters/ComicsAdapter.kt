
package com.xkcd.comics.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.xkcd.comics.R
import com.xkcd.comics.databinding.ComicsItemLayoutBinding
import com.xkcd.comics.model.XkcdComicsResponseModel.Data.Result

class ComicsAdapter(val onComicSelected: (comic: Result, position: Int) -> Unit) : RecyclerView.Adapter<ComicsAdapter.XkcdComicViewHolder>() {

    private val comicItems: ArrayList<Result> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): XkcdComicViewHolder {
        val binding = ComicsItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return XkcdComicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: XkcdComicViewHolder, position: Int) {
        holder.bind(comicItems[position], position)
    }

    override fun getItemCount() = comicItems.size

    fun updateItems(comicsList: List<Result>) {
        comicItems.clear()
        comicItems.addAll(comicsList)
        notifyDataSetChanged()
    }

    inner class XkcdComicViewHolder(val itemBinding: ComicsItemLayoutBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(comicModel: Result, position: Int) {
            itemBinding.apply {
                imgComics.load(comicModel.images[0].path.plus(comicModel.images[0].extension)) {
                    placeholder(R.color.color_box_background)
                    crossfade(true)
                }

                cardComics.setOnClickListener {
                    onComicSelected(comicModel, position)
                }
            }
        }
    }
}
