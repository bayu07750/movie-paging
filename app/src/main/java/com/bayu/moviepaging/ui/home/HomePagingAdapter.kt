package com.bayu.moviepaging.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.bayu.moviepaging.databinding.ItemMediaHomeBinding
import com.bayu.moviepaging.domain.model.Media

class HomePagingAdapter : PagingDataAdapter<Media, HomePagingAdapter.HomePagingViewHolder>(
    diffCallback
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomePagingViewHolder {
        return HomePagingViewHolder(
            ItemMediaHomeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HomePagingViewHolder, position: Int) {
        val media = getItem(position)
        media?.let {
            holder.bind(it)
        }
    }

    inner class HomePagingViewHolder(
        private val binding: ItemMediaHomeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(media: Media) {
            with(binding) {
                ivPoster.load(media.parsePoster()) {
                    crossfade(true)
                    crossfade(300)
                    transformations(RoundedCornersTransformation(16F))
                }
            }
        }

    }

    companion object {
        internal val diffCallback = object : DiffUtil.ItemCallback<Media>() {
            override fun areItemsTheSame(oldItem: Media, newItem: Media): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Media, newItem: Media): Boolean {
                return oldItem == newItem
            }

        }
    }

}