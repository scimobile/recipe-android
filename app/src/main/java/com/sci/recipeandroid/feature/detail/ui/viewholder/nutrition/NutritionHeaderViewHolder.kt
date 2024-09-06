package com.sci.recipeandroid.feature.detail.ui.viewholder.nutrition

import com.sci.recipeandroid.databinding.NutritionHeaderViewholderBinding
import com.sci.recipeandroid.feature.detail.domain.model.NutritionHeaderModel
import com.sci.recipeandroid.feature.detail.domain.model.NutritionScreenData

class NutritionHeaderViewHolder(
    private val binding: NutritionHeaderViewholderBinding
):NutritionBaseViewHolder(binding){
    override fun bind(data: NutritionScreenData) {
        if (data is NutritionHeaderModel){
            binding.caloriesAmtTv.text = data.calories
        }
    }

}