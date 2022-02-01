package com.bayu.moviepaging.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bayu.moviepaging.databinding.ItemKeywordBinding
import com.bayu.moviepaging.domain.model.Keyword

class SearchAdapter(
    private val onClickItem: (Keyword) -> Unit,
    private val onClickBtnUseKeyword: (Keyword) -> Unit,
) : ListAdapter<Keyword, SearchAdapter.SearchViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            ItemKeywordBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }

    inner class SearchViewHolder(
        private val binding: ItemKeywordBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(keyword: Keyword) {
            with(binding) {
                tvKeyword.text = keyword.name
                btnSearch.setOnClickListener { onClickItem(keyword) }
                tvKeyword.setOnClickListener { onClickItem(keyword) }
                btnUseKeyword.setOnClickListener { onClickBtnUseKeyword(keyword) }
            }
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Keyword>() {
            override fun areItemsTheSame(oldItem: Keyword, newItem: Keyword): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Keyword, newItem: Keyword): Boolean {
                return oldItem == newItem
            }
        }
    }

}