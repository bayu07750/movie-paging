package com.bayu.moviepaging.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bayu.moviepaging.databinding.FragmentFavoriteBinding
import com.bayu.moviepaging.ui.base.fragment.BaseFragment

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFavoriteBinding =
        { inflater, parent, attach ->
            FragmentFavoriteBinding.inflate(inflater, parent, attach)
        }

}