package com.sci.recipeandroid.feature.detail.ui.viewholder.detail.detailcenter

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sci.recipeandroid.R
import com.sci.recipeandroid.databinding.ItemViewDetailCenterBinding
import com.sci.recipeandroid.feature.detail.ui.adapter.detailcenter.DetailIngredientAdapter
import com.sci.recipeandroid.feature.detail.ui.models.DetailCenterUiModels
import com.sci.recipeandroid.util.setOneTimeClickListener

@SuppressLint("SetTextI18n")
class DetailCenterViewHolder(
    private val binding: ItemViewDetailCenterBinding,
    private val giveAmountResult: (Int) -> Unit,
    private val goToViewDirection: () -> Unit,
    private val goToAllNutrition: () -> Unit,
    private val addToCart: () -> Unit,
    private val onSelectUnit: (String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    private val ingredientAdapter = DetailIngredientAdapter()
    private val proteinAmtTv =binding.proteinAmtTv
    private val fatAmtTv = binding.fatAmtTv
    private val calorieAmtTv = binding.calorieAmtTv
    private val carbAmtTv = binding.carbAmtTv
    private val serveAmtTv = binding.serveAmtTv
    private val usUnitTv = binding.usUnitTv
    private val metricTv = binding.metricTv
    private val addToCartBtn = binding.addToCartBtn
    private val viewAllTv = binding.viewAllTv
    private val viewDirectionTv = binding.viewDirectionTv
    private val addBtn = binding.addBtn
    private val removeBtn = binding.removeBtn

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
    fun bind(detailCenterItemData: DetailCenterUiModels) {

        proteinAmtTv.text = detailCenterItemData.nutritionPerServeModel.protein
        fatAmtTv.text = detailCenterItemData.nutritionPerServeModel.fat
        calorieAmtTv.text = detailCenterItemData.nutritionPerServeModel.calories
        carbAmtTv.text = detailCenterItemData.nutritionPerServeModel.carbs
        serveAmtTv.text = "Serve ${detailCenterItemData.serveAmt}"
        if (detailCenterItemData.isAddedToCart){
            addToCartBtn.isEnabled = false
            addToCartBtn.backgroundTintList = ColorStateList.valueOf(
                itemView.context.getColor(R.color.color_button_inactive)
            )
            addToCartBtn.setTextColor(Color.WHITE)
        }else{
            addToCartBtn.isEnabled = true
            addToCartBtn.backgroundTintList = ColorStateList.valueOf(
                itemView.context.getColor(R.color.color_button_active)
            )
            addToCartBtn.setTextColor(Color.WHITE)
        }

        if (detailCenterItemData.selectedUnit == "us") {
            usUnitTv.setTextColor(Color.BLACK)
            metricTv.setTextColor(Color.GRAY)
        }else{
            metricTv.setTextColor(Color.BLACK)
            usUnitTv.setTextColor(Color.GRAY)
        }

        usUnitTv.setOnClickListener {
            onSelectUnit("us")
        }
        metricTv.setOnClickListener {
            onSelectUnit("metric")
        }

        addToCartBtn.setOnClickListener {
            addToCart()
        }

        addBtn.setOnClickListener {
            giveAmountResult(detailCenterItemData.serveAmt + 1)
        }

        removeBtn.setOnClickListener {
            if (detailCenterItemData.serveAmt > 1) {
                giveAmountResult(detailCenterItemData.serveAmt - 1)
            }
        }

        viewAllTv.setOneTimeClickListener {
            goToAllNutrition()
        }

        viewDirectionTv.setOneTimeClickListener {
            goToViewDirection()
        }

        ingredientAdapter.updateList(
            ingredientLists = detailCenterItemData.ingredients
        )
    }
}