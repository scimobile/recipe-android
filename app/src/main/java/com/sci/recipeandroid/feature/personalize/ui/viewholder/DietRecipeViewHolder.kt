package com.sci.recipeandroid.feature.personalize.ui.viewholder

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sci.recipeandroid.R
import com.sci.recipeandroid.databinding.ItemViewDietRecipeBinding
import com.sci.recipeandroid.feature.personalize.domain.model.DietRecipeModel

class DietRecipeViewHolder(
    private val binding: ItemViewDietRecipeBinding,
    private val onClickItem: (Boolean) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: DietRecipeModel, isSelected: Boolean) {
        binding.apply {
            tvRecipeDiet.text = model.name
            Glide.with(itemView.context)
                .load(model.imageUrl)
                .error(R.drawable.none)
                .into(ivRecipeDiet)

            cardDietRecipe.strokeColor = if (isSelected) {
                ContextCompat.getColor(itemView.context, R.color.color_primary)
            } else {
                ContextCompat.getColor(itemView.context, R.color.color_surface)
            }
        }
    }

    fun setSelectionCallback(callback: () -> Unit) {
        itemView.setOnClickListener {
            onClickItem(adapterPosition == RecyclerView.NO_POSITION)
            callback()
        }
    }
}
