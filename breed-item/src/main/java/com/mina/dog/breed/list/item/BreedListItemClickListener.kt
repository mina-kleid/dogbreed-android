package com.mina.dog.breed.list.item

import com.mina.dog.breed.common.models.Breed

public interface BreedListItemClickListener {
    fun onItemClicked(breed: Breed)
}