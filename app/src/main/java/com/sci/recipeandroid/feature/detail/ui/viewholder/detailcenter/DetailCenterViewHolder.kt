package com.sci.recipeandroid.feature.detail.ui.viewholder.detailcenter

import android.annotation.SuppressLint
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sci.recipeandroid.databinding.DetailCenterViewholderBinding
import com.sci.recipeandroid.feature.detail.domain.model.DetailCenterContainer
import com.sci.recipeandroid.feature.detail.ui.adapter.detailcenter.DetailIngredientAdapter
import com.sci.recipeandroid.util.setOneTimeClickListener
import kotlin.properties.Delegates

@SuppressLint("SetTextI18n")
class DetailCenterViewHolder(
    private val binding: DetailCenterViewholderBinding,
    private val giveAmountResult: (Int) -> Unit,
    private val goToViewDirection: () -> Unit,
    private val goToAllNutrition: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    private val ingredientAdapter = DetailIngredientAdapter()
    private var serveAmt by Delegates.observable(1) { property, oldValue, newValue ->
        binding.serveAmtTv.text = "Serve $newValue"
        giveAmountResult(newValue)
    }

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
        binding.serveAmtTv.text = "Serve $serveAmt"
        binding.addBtn.setOnClickListener {
            serveAmt += 1
        }
        binding.removeBtn.setOnClickListener {
            if (serveAmt > 1) {
                serveAmt -= 1
            }
        }

        binding.viewAllTv.setOneTimeClickListener {
            goToAllNutrition()
        }

        binding.viewDirectionTv.setOneTimeClickListener {
            goToViewDirection()
        }

        ingredientAdapter.updateList(
            ingredientLists = detailCenterItemData.ingredients
        )
    }
}