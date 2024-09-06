package com.sci.recipeandroid.feature.detail.ui.viewholder.nutrition

import com.sci.recipeandroid.databinding.NutritionDisclaimerViewholderBinding
import com.sci.recipeandroid.feature.detail.domain.model.NutritionFooterModel
import com.sci.recipeandroid.feature.detail.domain.model.NutritionScreenData

class NutritionFooterViewHolder(
    private val binding: NutritionDisclaimerViewholderBinding
):NutritionBaseViewHolder(binding) {
    override fun bind(data: NutritionScreenData) {
        if (data is NutritionFooterModel){
            binding.noticeTv.text = data.notice
            binding.diclaimeTv.text = data.disClaimer
        }
    }
}