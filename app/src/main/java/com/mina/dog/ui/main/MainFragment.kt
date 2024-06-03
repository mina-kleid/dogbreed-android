package com.mina.dog.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.mina.dog.R
import com.mina.dog.databinding.MainFragmentBinding
import com.mina.dog.ui.main.tabs.TabCollectionAdapter

class MainFragment : Fragment() {
    private lateinit var binding: MainFragmentBinding

    private lateinit var tabCollectionAdapter: TabCollectionAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        tabCollectionAdapter = TabCollectionAdapter(this)
        viewPager = binding.pager
        viewPager.adapter = tabCollectionAdapter

        TabLayoutMediator(binding.tabLayout, viewPager) { tab, position ->
            tab.text = tabCollectionAdapter.tabs[position].title
        }.attach()

        (requireActivity() as AppCompatActivity).supportActionBar?.title =
            getString(R.string.app_name)
    }
}
