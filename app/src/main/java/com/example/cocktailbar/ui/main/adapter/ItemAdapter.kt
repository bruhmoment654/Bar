package com.example.cocktailbar.ui.main.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cocktailbar.entity.Cocktail
import com.example.cocktailbar.databinding.ItemCocktailBinding

class ItemAdapter(
    val onClick: (Cocktail) -> Unit
) : ListAdapter<Cocktail, ItemAdapter.Holder>(DiffUtils()) {


    inner class Holder(
        private val binding: ItemCocktailBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Cocktail) = with(binding) {
            tvItemHead.text = item.name
            ivImageCocktail.setImageURI(item.image)
            ivImageCocktail.setOnClickListener { view ->
                onClick(item)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            ItemCocktailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(currentList[position])
    }

    private class DiffUtils : DiffUtil.ItemCallback<Cocktail>() {
        override fun areItemsTheSame(oldItem: Cocktail, newItem: Cocktail): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Cocktail, newItem: Cocktail): Boolean =
            oldItem.id == newItem.id

    }


    companion object {
        const val EXTRA_COCKTAIL = "EXTRA_COCKTAIL"

        fun composeParams(cocktail: Cocktail): Bundle = bundleOf(
            EXTRA_COCKTAIL to cocktail
        )
    }

}