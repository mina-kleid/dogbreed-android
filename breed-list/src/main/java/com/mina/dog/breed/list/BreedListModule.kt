package com.mina.dog.breed.list

import androidx.fragment.app.Fragment
import com.mina.dog.breed.list.item.BreedListItemClickListener
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(FragmentComponent::class)
internal object BreedListFragmentModule {

    @Provides
    fun itemClickListener(fragment: Fragment): BreedListItemClickListener =
        fragment as BreedListItemClickListener

    @Provides
    fun dispatcher(): CoroutineDispatcher = Dispatchers.IO
}

@Module
@InstallIn(ViewModelComponent::class)
internal object BreedListViewModelModule {

    @Provides
    fun dispatcher(): CoroutineDispatcher = Dispatchers.IO
}