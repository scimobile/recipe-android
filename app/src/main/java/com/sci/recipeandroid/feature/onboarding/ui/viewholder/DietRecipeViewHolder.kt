package com.sci.recipeandroid.feature.onboarding.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sci.recipeandroid.R
import com.sci.recipeandroid.databinding.ItemViewDietRecipeBinding
import com.sci.recipeandroid.feature.onboarding.domain.model.DietRecipeModel

class DietRecipeViewHolder(private val binding: ItemViewDietRecipeBinding,private val onClickItem: (Int) -> Unit) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: DietRecipeModel) {
        binding.apply {
            tvRecipeDiet.text = model.name
            Glide.with(itemView.context)
                .load(model.imageUrl)
        }
        itemView.setOnClickListener {
            binding.container.setBackgroundResource(R.drawable.ic_clickabel_bg)
            onClickItem(
                adapterPosition
            )
        }
    }
}