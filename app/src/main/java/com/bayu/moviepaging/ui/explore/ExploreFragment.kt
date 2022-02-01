package com.bayu.moviepaging.ui.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bayu.moviepaging.databinding.FragmentExploreBinding
import com.bayu.moviepaging.ui.base.fragment.BaseFragment

class ExploreFragment : BaseFragment<FragmentExploreBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentExploreBinding =
        { inflater, parent, attach ->
            FragmentExploreBinding.inflate(inflater, parent, attach)
        }

}