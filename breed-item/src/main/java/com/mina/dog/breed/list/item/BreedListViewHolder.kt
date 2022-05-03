package com.mina.dog.breed.list.item

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mina.dog.breed.common.models.Breed

public class BreedListViewHolder(
    itemView: View,
) : RecyclerView.ViewHolder(itemView) {

    fun bind(breed: Breed, itemClickListener: BreedListItemClickListener) {
        val breedName: TextView = itemView.findViewById(R.id.breedName)
        val subBreeds: TextView = itemView.findViewById(R.id.subBreeds)

        breedName.text = breed.name
        subBreeds.text = breed.subBreeds.size.toString()

        itemView.setOnClickListener { itemClickListener.onItemClicked(breed) }
    }
}