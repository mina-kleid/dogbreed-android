package com.mina.dog.breed.ui.main.tabs

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mina.dog.breed.list.BreedListFragment


internal class TabCollectionAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return TAB_COUNT
    }


    override fun createFragment(position: Int): Fragment {

        return TABS[position].fragment
    }

    companion object {
        const val TAB_COUNT: Int = 2
        val TABS: List<Tab> = listOf(
            Tab("Breeds", BreedListFragment()),
            Tab("Favorites", Fragment()),
        )
    }

}