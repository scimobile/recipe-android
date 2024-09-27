package com.sci.recipeandroid.feature.detail.ui.viewholder.nutrition

import com.sci.recipeandroid.databinding.ItemViewNutritionBinding
import com.sci.recipeandroid.feature.detail.domain.model.NutritionModel
import com.sci.recipeandroid.feature.detail.domain.model.NutritionScreenData

class NutritionViewHolder(
    private val binding: ItemViewNutritionBinding
):NutritionBaseViewHolder(binding) {
    private val nutritionNameTv = binding.nutritionTv
    private val nutritionAmountTv = binding.nutritionAmtTv
    private val dailyValueTv = binding.dailyValueTv

    override fun bind(data: NutritionScreenData) {
        if (data is NutritionModel){
            nutritionNameTv.text = data.typeName
            nutritionAmountTv.text = data.amount
            dailyValueTv.text = data.dailyValue
        }
    }

}