package com.mina.dog.breed.list.item

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mina.dog.breed.common.models.Breed

public class BreedListViewHolder(
    itemView: View,
    private val requestOptions: RequestOptions,
) : RecyclerView.ViewHolder(itemView) {

    fun bind(breed: Breed, itemClickListener: BreedListItemClickListener) {
        val breedName: TextView = itemView.findViewById(R.id.breedName)
        val subBreeds: TextView = itemView.findViewById(R.id.subBreeds)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)

        breedName.text = breed.name
        subBreeds.text = itemView
            .context
            .getString(R.string.sub_breeds_label, breed.subBreeds.size.toString())

        Glide
            .with(itemView.context)
            .load(breed.imageUrl())
            .apply(requestOptions)
            .into(imageView)

        itemView.setOnClickListener { itemClickListener.onItemClicked(breed) }
    }
}