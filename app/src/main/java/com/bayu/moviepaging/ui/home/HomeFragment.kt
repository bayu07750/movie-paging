package com.bayu.moviepaging.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.bayu.moviepaging.R
import com.bayu.moviepaging.core.enums.MediaType
import com.bayu.moviepaging.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), AdapterView.OnItemClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val spinnerItems = listOf(MediaType.ALL, MediaType.MOVIE, MediaType.TV).map { it.type }

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
    }

    private fun init() {
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        val mediaType = MediaType.valueOf(spinnerItems[position].uppercase())
        Log.d("TAG", "onItemClick: $mediaType")
    }
}