package com.mina.dog.breed.common.ui

import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent


@Module
@InstallIn(ActivityComponent::class)
object CommonUiModule {

    @Provides
    fun glideRequestOptions(): RequestOptions =
        RequestOptions()
            .placeholder(R.drawable.ic_image_loading)
            .error(R.drawable.ic_image_loading_error)
            .fallback(R.drawable.ic_image_loading_error)
            .fitCenter()
}