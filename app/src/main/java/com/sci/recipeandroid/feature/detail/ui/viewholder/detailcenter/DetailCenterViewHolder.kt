package com.sci.recipeandroid.feature.detail.ui.viewholder.detailcenter

import android.annotation.SuppressLint
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sci.recipeandroid.databinding.DetailCenterViewholderBinding
import com.sci.recipeandroid.feature.detail.domain.model.DetailCenterContainer
import com.sci.recipeandroid.feature.detail.ui.adapter.detailcenter.DetailIngredientAdapter
import com.sci.recipeandroid.util.setOneTimeClickListener

class DetailCenterViewHolder(
    private val binding: DetailCenterViewholderBinding
) : RecyclerView.ViewHolder(binding.root) {
    private val ingredientAdapter = DetailIngredientAdapter()
    private var serveAmt = 1

    init {
        binding.detailIngredientRv.apply {
            this.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
        }.adapter = ingredientAdapter
    }

    @SuppressLint("SetTextI18n")
    fun bind(detailCenterItemData: DetailCenterContainer) {

        binding.proteinAmtTv.text = detailCenterItemData.nutritionPerServing.protein
        binding.fatAmtTv.text = detailCenterItemData.nutritionPerServing.fat
        binding.calorieAmtTv.text = detailCenterItemData.nutritionPerServing.calories
        binding.carbAmtTv.text = detailCenterItemData.nutritionPerServing.carbs


        ingredientAdapter.updateList(
            ingredientLists = detailCenterItemData.ingredients
        )
    }
}