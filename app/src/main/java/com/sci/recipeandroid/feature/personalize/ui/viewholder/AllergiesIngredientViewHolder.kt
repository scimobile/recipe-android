package com.sci.recipeandroid.feature.personalize.ui.viewholder

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.sci.recipeandroid.R
import com.sci.recipeandroid.databinding.ItemViewAllergiesIngredientBinding
import com.sci.recipeandroid.feature.personalize.ui.model.AllergiesIngredientUiModel


class AllergiesIngredientViewHolder(
    private val binding: ItemViewAllergiesIngredientBinding,
    private val onClickItem: (String?) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(allergiesIngredientUiModel: AllergiesIngredientUiModel) {
        binding.tvName.text = allergiesIngredientUiModel.allergiesIngredientModel.name

        if (allergiesIngredientUiModel.isSelect) {
            binding.cardAllergiesIngredient.strokeColor = ContextCompat.getColor(itemView.context, R.color.color_primary)
            binding.tvName.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.color_primary))
            binding.tvName.setTextColor(ContextCompat.getColor(itemView.context, R.color.color_surface))
        } else {
            binding.cardAllergiesIngredient.strokeColor = ContextCompat.getColor(itemView.context, R.color.gray_2)
            binding.tvName.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.color_surface))
            binding.tvName.setTextColor(ContextCompat.getColor(itemView.context, R.color.color_text_secondary))
        }

        binding.root.setOnClickListener {
            onClickItem(allergiesIngredientUiModel.allergiesIngredientModel.id)
        }
    }
}