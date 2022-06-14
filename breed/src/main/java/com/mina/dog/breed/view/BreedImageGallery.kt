package com.mina.dog.breed.view

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import com.bumptech.glide.request.RequestOptions
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.glide.LocalGlideRequestOptions

@Composable
fun BreedImageGallery(images: List<String>, modifier: Modifier = Modifier) {
    LazyRow {
        items(images) {
            ImageItem(imageUrl = it)
        }
    }
}

@Composable
fun ImageItem(imageUrl: String, modifier: Modifier = Modifier) {
    GlideImage(
        imageModel = imageUrl,
        requestOptions = { LocalGlideRequestOptions.current ?: RequestOptions() }
    )
}