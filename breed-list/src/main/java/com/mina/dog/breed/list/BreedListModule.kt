package com.mina.dog.breed.list

import androidx.fragment.app.Fragment
import com.mina.dog.breed.list.item.BreedListItemClickListener
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
internal object BreedListModule {

    @Provides
    fun itemClickListener(fragment: Fragment): BreedListItemClickListener =
        fragment as BreedListItemClickListener
}