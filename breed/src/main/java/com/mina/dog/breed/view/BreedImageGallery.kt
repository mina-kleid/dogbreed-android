package com.mina.dog.breed.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bumptech.glide.request.RequestOptions
import com.mina.dog.breed.common.ui.R
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.glide.LocalGlideRequestOptions

@Composable
fun BreedImageGallery(images: List<String>, modifier: Modifier = Modifier) {
    LazyRow(modifier = modifier.fillMaxWidth().padding(24.dp)) {
        items(images) {
            ImageItem(imageUrl = it, modifier = modifier)
        }
    }
}

@Composable
fun ImageItem(imageUrl: String, modifier: Modifier = Modifier) {
    GlideImage(
        modifier = modifier.height(256.dp).fillMaxWidth(),
        imageModel = imageUrl,
        requestOptions = { LocalGlideRequestOptions.current ?: RequestOptions() }
    )
}