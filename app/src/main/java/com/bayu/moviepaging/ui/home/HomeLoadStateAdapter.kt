package com.bayu.moviepaging.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bayu.moviepaging.databinding.LoadStatePagingFooterBinding

class HomeLoadStateAdapter(
    private val onClickBtnRetry: () -> Unit
) : LoadStateAdapter<HomeLoadStateAdapter.HomeLoadStateViewHolder>() {

    inner class HomeLoadStateViewHolder(
        private val binding: LoadStatePagingFooterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnRetry.setOnClickListener {
                onClickBtnRetry.invoke()
            }
        }

        fun bind(loadState: LoadState) {
            with(binding) {
                progressBar.isVisible = loadState is LoadState.Loading
                btnRetry.isVisible = loadState !is LoadState.Loading
            }
        }
    }

    override fun onBindViewHolder(holder: HomeLoadStateViewHolder, loadState: LoadState) {
        return holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): HomeLoadStateViewHolder {
        return HomeLoadStateViewHolder(
            LoadStatePagingFooterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}