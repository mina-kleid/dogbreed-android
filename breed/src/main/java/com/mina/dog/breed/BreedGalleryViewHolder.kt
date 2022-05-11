package com.mina.dog.breed

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

internal class BreedGalleryViewHolder(
    itemView: View,
    private val requestOptions: RequestOptions) :
    RecyclerView.ViewHolder(itemView) {

        fun bind(imageUrl: String) {
            val imageView: ImageView = itemView.findViewById(R.id.imageView)

            Glide
                .with(itemView.context)
                .load(imageUrl)
                .apply(requestOptions)
                .into(imageView)
        }
}