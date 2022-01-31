package com.bayu.moviepaging.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.viewpager2.widget.ViewPager2
import com.bayu.moviepaging.R
import com.bayu.moviepaging.core.enums.MediaType
import com.bayu.moviepaging.core.ui.HorizontalMarginItemDecoration
import com.bayu.moviepaging.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.math.abs

@AndroidEntryPoint
class HomeFragment : Fragment(), AdapterView.OnItemClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()
    private val spinnerItems = listOf(MediaType.ALL.type, MediaType.MOVIE.type, MediaType.TV.type)

    private lateinit var homePagingAdapter: HomePagingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        observe()
        actions()
    }

    private fun actions() {
        binding.btnRetry.setOnClickListener {
            homePagingAdapter.retry()
        }

        binding.btnSearch.setOnClickListener {
            findNavController().navigate(R.id.action_nav_home_to_searchFragment)
        }
    }

    private fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.trending.collectLatest {
                homePagingAdapter.submitData(it)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        setupAutoCompleteTextView()
    }

    private fun setupAutoCompleteTextView() {
        val arrayAdapter = ArrayAdapter(
            requireContext(),
            R.layout.dropdown_item,
            spinnerItems
        )
        with(binding.autoCompleteTextView) {
            setAdapter(arrayAdapter)
            onItemClickListener = this@HomeFragment
        }
    }

    private fun init() {
        setupViewPager2()
    }

    private fun setupViewPager2() {
        val loadStateFooter = HomeLoadStateAdapter {
            homePagingAdapter.retry()
        }
        homePagingAdapter = HomePagingAdapter()
        homePagingAdapter.withLoadStateHeaderAndFooter(loadStateFooter, loadStateFooter)

        homePagingAdapter.addLoadStateListener { loadState ->
            val currentState = loadState.source.refresh
            with(binding) {
                progressBar.isVisible = currentState is LoadState.Loading
                viewPager.isVisible = currentState is LoadState.NotLoading
                btnRetry.isVisible = currentState is LoadState.Error
                tvMessage.isVisible = currentState is LoadState.Error
                tvMessage.text = getString(R.string.message_error)
            }
        }

        val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
        val currentItemHorizontalMarginPx =
            resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx

        val pageTransformer = ViewPager2.PageTransformer { page, position ->
            page.translationX = -pageTranslationX * position
            page.scaleY = 1 - (0.25F * abs(position))
        }

        val itemDecoration = HorizontalMarginItemDecoration(
            requireContext(),
            R.dimen.viewpager_current_item_horizontal_margin,
            R.dimen.viewpager_current_item_vertical_margin,
        )

        with(binding.viewPager) {
            adapter = homePagingAdapter
            offscreenPageLimit = 1
            setPageTransformer(pageTransformer)
            addItemDecoration(itemDecoration)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        binding.viewPager.setCurrentItem(0, true)
        val mediaType = MediaType.valueOf(spinnerItems[position].uppercase())
        viewModel.setMediaType(mediaType)
    }
}