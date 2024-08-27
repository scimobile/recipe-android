package com.sci.recipeandroid.feature.personalize.ui.viewholder

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sci.recipeandroid.R
import com.sci.recipeandroid.databinding.ItemViewDietRecipeBinding
import com.sci.recipeandroid.feature.personalize.ui.model.DietRecipeUiModel

class DietRecipeViewHolder(
    private val binding: ItemViewDietRecipeBinding,
    private val onClickItem: (String?) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(dietRecipeUiModel: DietRecipeUiModel) {
        binding.tvName.text = dietRecipeUiModel.dietRecipeModel.name
        Glide.with(binding.ivImage.context)
            .load(dietRecipeUiModel.dietRecipeModel.imageUrl)
            .into(binding.ivImage)

        if (dietRecipeUiModel.isSelect) {
            binding.cardDietRecipe.strokeColor = ContextCompat.getColor(itemView.context, R.color.color_primary)
        } else {
            binding.cardDietRecipe.strokeColor = ContextCompat.getColor(itemView.context, R.color.color_surface)
        }

        binding.root.setOnClickListener {
            onClickItem(dietRecipeUiModel.dietRecipeModel.id)
        }
    }
}
