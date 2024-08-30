package com.sci.recipeandroid.feature.detail.ui.viewholder.detailfooter

import com.sci.recipeandroid.databinding.DetailRecipeOwnerViewholderBinding
import com.sci.recipeandroid.feature.detail.domain.model.DetailFooterItem
import com.sci.recipeandroid.feature.detail.domain.model.RecipeOwnerContainer

class RecipeOwnerViewHolder(
    private val binding: DetailRecipeOwnerViewholderBinding
):DetailFooterBaseViewHolder(binding){
    override fun bind(data: DetailFooterItem) {
        if (data is RecipeOwnerContainer){
            binding.recipeOwnerNameTv.text = data.recipeOwnerName
            binding.recipeOwnerNameTv.text = data.ownerDescription
        }
    }
}