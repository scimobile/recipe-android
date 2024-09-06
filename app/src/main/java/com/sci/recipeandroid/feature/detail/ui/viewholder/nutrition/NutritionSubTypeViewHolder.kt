package com.sci.recipeandroid.feature.detail.ui.viewholder.nutrition

import com.sci.recipeandroid.databinding.NutritionSubTypeViewholderBinding
import com.sci.recipeandroid.feature.detail.domain.model.NutritionScreenData
import com.sci.recipeandroid.feature.detail.domain.model.SubTypeNutritionModel

class NutritionSubTypeViewHolder(
    private val binding: NutritionSubTypeViewholderBinding
) : NutritionBaseViewHolder(binding) {
    private val subNutritionNameTv = binding.nutritionTv
    private val subNutritionAmtTv = binding.nutritionAmtTv
    private val subDailyValueTv = binding.dailyValueTv

    override fun bind(data: NutritionScreenData) {
        if (data is SubTypeNutritionModel){
            subNutritionNameTv.text = data.subName
            subNutritionAmtTv.text = data.subAmount
            subDailyValueTv.text = data.subDailyValue
        }
    }

}