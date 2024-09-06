package com.sci.recipeandroid.feature.detail.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sci.recipeandroid.databinding.NutritionDisclaimerViewholderBinding
import com.sci.recipeandroid.databinding.NutritionHeaderViewholderBinding
import com.sci.recipeandroid.databinding.NutritionSubTypeViewholderBinding
import com.sci.recipeandroid.databinding.NutritionViewholderBinding
import com.sci.recipeandroid.feature.detail.domain.model.NutritionFooterModel
import com.sci.recipeandroid.feature.detail.domain.model.NutritionHeaderModel
import com.sci.recipeandroid.feature.detail.domain.model.NutritionModel
import com.sci.recipeandroid.feature.detail.domain.model.NutritionScreenData
import com.sci.recipeandroid.feature.detail.domain.model.SubTypeNutritionModel
import com.sci.recipeandroid.feature.detail.ui.viewholder.nutrition.NutritionBaseViewHolder
import com.sci.recipeandroid.feature.detail.ui.viewholder.nutrition.NutritionFooterViewHolder
import com.sci.recipeandroid.feature.detail.ui.viewholder.nutrition.NutritionHeaderViewHolder
import com.sci.recipeandroid.feature.detail.ui.viewholder.nutrition.NutritionSubTypeViewHolder
import com.sci.recipeandroid.feature.detail.ui.viewholder.nutrition.NutritionViewHolder

class NutritionAdapter : RecyclerView.Adapter<NutritionBaseViewHolder>() {
    companion object {
        const val TYPE_HEADER = 0
        const val TYPE_NUTRITION_PARENT = 1
        const val TYPE_SUB_TYPE = 2
        const val TYPE_FOOTER = 3
    }
    private var nutritionData = emptyList<NutritionScreenData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NutritionBaseViewHolder {
        when(viewType){
            TYPE_HEADER -> NutritionHeaderViewholderBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ).apply {
                return NutritionHeaderViewHolder(this)
            }
            TYPE_NUTRITION_PARENT -> NutritionViewholderBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ).apply {
                return NutritionViewHolder(this)
            }
            TYPE_SUB_TYPE -> NutritionSubTypeViewholderBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ).apply {
                return NutritionSubTypeViewHolder(this)
            }
            TYPE_FOOTER -> NutritionDisclaimerViewholderBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ).apply {
                return NutritionFooterViewHolder(this)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int {
        return nutritionData.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(nutritionList: List<NutritionScreenData>) {
        nutritionData = nutritionList
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when(nutritionData[position]){
            is NutritionHeaderModel -> TYPE_HEADER
            is NutritionModel -> TYPE_NUTRITION_PARENT
            is SubTypeNutritionModel -> TYPE_SUB_TYPE
            is NutritionFooterModel -> TYPE_FOOTER
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }


    override fun onBindViewHolder(holder: NutritionBaseViewHolder, position: Int) {
        holder.bind(nutritionData[position])
    }
}