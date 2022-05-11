package com.mina.dog.breed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import javax.inject.Inject

internal class BreedGalleryAdapter @Inject constructor(
    private val requestOptions: RequestOptions
) : RecyclerView.Adapter<BreedGalleryViewHolder>() {

    private var images: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedGalleryViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.breed_gallery_item, parent, false)
        return BreedGalleryViewHolder(itemView, requestOptions)
    }

    override fun onBindViewHolder(holder: BreedGalleryViewHolder, position: Int) {
        val imageUrl: String = images[position]
        holder.bind(imageUrl)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    fun updateItems(images: List<String>) {
        this.images.clear()
        this.images.addAll(images)
        notifyDataSetChanged()
    }
}