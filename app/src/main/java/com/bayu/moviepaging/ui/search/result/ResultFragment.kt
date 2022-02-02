package com.bayu.moviepaging.ui.search.result

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bayu.moviepaging.databinding.FragmentResultBinding
import com.bayu.moviepaging.ui.base.fragment.BaseFragment

class ResultFragment : BaseFragment<FragmentResultBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentResultBinding =
        { inflater, parent, attach ->
            FragmentResultBinding.inflate(inflater, parent, attach)
        }

    private val navArgs: ResultFragmentArgs by navArgs()

    override fun initView() {
        super.initView()

        val searchQuery = navArgs.searchQuery

        if (searchQuery.isNotEmpty()) {
            binding.edtQuery.setText(searchQuery)
        } else {
            findNavController().navigateUp()
        }
    }

    override fun actions() {
        super.actions()

        binding.btnBack.setOnClickListener { findNavController().navigateUp() }
    }

}