package com.mina.dog.breed.ui.main.tabs

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mina.dog.breed.list.BreedListFragment


internal class TabCollectionAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val tabCount: Int = 2
    val tabs: List<Tab> = listOf(
        Tab("Breeds", TabFragment.BREEDS),
        Tab("Favorites", TabFragment.NOT_IMPLEMENTED),
    )

    override fun getItemCount(): Int {
        return tabCount
    }


    override fun createFragment(position: Int): Fragment =
        when (tabs[position].tabFragment) {
            TabFragment.BREEDS -> BreedListFragment()
            TabFragment.NOT_IMPLEMENTED -> Fragment()
        }

}