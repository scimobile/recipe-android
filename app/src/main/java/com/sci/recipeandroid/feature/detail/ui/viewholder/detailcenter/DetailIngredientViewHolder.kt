package com.sci.recipeandroid.feature.detail.ui.viewholder.detailcenter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.sci.recipeandroid.databinding.DetailIngredientViewholderBinding
import com.sci.recipeandroid.feature.detail.domain.model.Ingredients

class DetailIngredientViewHolder(private val binding: DetailIngredientViewholderBinding) :
    RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("SetTextI18n")
    fun bind(ingredient: Ingredients) {
        binding.ingredientNameTv.text = ingredient.name
        binding.ingredientAmountTv.text = ingredient.amount.toString() + ingredient.amountUnit
    }
}