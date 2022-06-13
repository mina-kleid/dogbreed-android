package com.mina.dog.breed.view

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.items
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun BreedImageGallery(images: List<String>) {
    LazyRow {
        items(images) {
            ImageItem(imageUrl = it)
        }
    }
}

@Composable
fun ImageItem(imageUrl: String) {
    GlideImage(imageModel = imageUrl)
}