package com.bayu.moviepaging.ui.search

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bayu.moviepaging.core.data.vo.Resource
import com.bayu.moviepaging.databinding.FragmentSearchBinding
import com.bayu.moviepaging.domain.model.Keyword
import com.bayu.moviepaging.ui.base.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchBinding =
        { inflater, parent, attachParent ->
            FragmentSearchBinding.inflate(inflater, parent, attachParent)
        }

    private val viewModel: SearchViewModel by viewModels()
    private val handler = Handler(Looper.getMainLooper())
    private var querySearch = ""

    private lateinit var searchAdapter: SearchAdapter
    private lateinit var runnable: Runnable

    override fun initView() {
        super.initView()
        searchAdapter = SearchAdapter(
            onClickItem = onClickItem,
            onClickBtnUseKeyword = onCLickBtnUseKeyword,
        )
        setupRecyclerView()
        runnable = Runnable {
            viewModel.setQuery(querySearch)
        }
    }

    private fun setupRecyclerView() {
        with(binding.recyclerView) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = searchAdapter
        }
    }

    override fun observe() {
        super.observe()
        lifecycleScope.launch {
            repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                viewModel.keywords.collectLatest { result ->
                    when (result) {
                        is Resource.Error -> {} // TODO
                        is Resource.Loading -> {}// TODO
                        is Resource.Success -> {
                            searchAdapter.submitList(result.data)
                        }
                    }
                }
            }
        }
    }

    override fun actions() {
        super.actions()
        with(binding) {
            btnBack.setOnClickListener { findNavController().navigateUp() }

            editSearch.addTextChangedListener {
                querySearch = it?.toString().orEmpty()
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 600)
            }
        }
    }

    private val onClickItem: (Keyword) -> Unit = {
        val directions = SearchFragmentDirections.actionSearchFragmentToResultFragment(it.name)
        findNavController().navigate(directions)
    }

    private val onCLickBtnUseKeyword: (Keyword) -> Unit = {
        binding.editSearch.setText(it.name)
    }
}