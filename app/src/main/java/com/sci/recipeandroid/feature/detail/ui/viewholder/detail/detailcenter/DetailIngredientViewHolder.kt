package com.sci.recipeandroid.feature.detail.ui.viewholder.detail.detailcenter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.sci.recipeandroid.databinding.ItemViewDetailIngredientBinding
import com.sci.recipeandroid.feature.detail.ui.models.IngredientUiModels

class DetailIngredientViewHolder(private val binding: ItemViewDetailIngredientBinding) :
    RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("SetTextI18n")
    fun bind(ingredient: IngredientUiModels) {
        binding.ingredientNameTv.text = ingredient.ingredientModel.name
        if (ingredient.selectedUnit == "us") {
            binding.ingredientAmountTv.text =
                "${ingredient.ingredientModel.amountPerUsUnit.usAmt}" +
                        " ${ingredient.ingredientModel.amountPerUsUnit.usUnit}"

        } else {
            binding.ingredientAmountTv.text =
                "${ingredient.ingredientModel.amountPerMetricUnit.metricAmt}" +
                        " ${ingredient.ingredientModel.amountPerMetricUnit.metricUnit}"
        }
    }
}