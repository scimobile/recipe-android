package com.sci.recipeandroid.feature.detail.ui.viewholder.nutrition

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.sci.recipeandroid.feature.detail.domain.model.NutritionScreenData

abstract class NutritionBaseViewHolder (binding:ViewBinding):RecyclerView.ViewHolder(
    binding.root
) {
    abstract fun bind(data:NutritionScreenData)
}