package com.mina.dog.breed.list.item

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mina.dog.breed.common.models.Breed
import javax.inject.Inject

public class BreedListAdapter @Inject constructor(
    private val itemClickListener: BreedListItemClickListener,
) : RecyclerView.Adapter<BreedListViewHolder>() {

    private val breeds: MutableList<Breed> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedListViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.breed_list_item, parent, false)
        return BreedListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BreedListViewHolder, position: Int) {
        holder.bind(breed = breeds[position], itemClickListener = itemClickListener)
    }

    override fun getItemCount(): Int = breeds.size

    fun updateAdapter(breeds: List<Breed>) {
        this.breeds.clear()
        this.breeds.addAll(breeds)
        this.notifyDataSetChanged()
    }
}