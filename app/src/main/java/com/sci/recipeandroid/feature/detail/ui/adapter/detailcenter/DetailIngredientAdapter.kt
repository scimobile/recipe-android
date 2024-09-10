package com.sci.recipeandroid.feature.detail.ui.adapter.detailcenter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sci.recipeandroid.databinding.DetailIngredientViewholderBinding
import com.sci.recipeandroid.feature.detail.domain.model.IngredientsModel
import com.sci.recipeandroid.feature.detail.ui.viewholder.detail.detailcenter.DetailIngredientViewHolder

class DetailIngredientAdapter() : RecyclerView.Adapter<DetailIngredientViewHolder>() {
    private var ingredientList = emptyList<IngredientsModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailIngredientViewHolder {
        DetailIngredientViewholderBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ).apply {
            return DetailIngredientViewHolder(this)
        }
    }

    override fun getItemCount(): Int {
        return ingredientList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(ingredientLists: List<IngredientsModel>) {
        ingredientList = ingredientLists
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: DetailIngredientViewHolder, position: Int) {
        holder.bind(ingredientList[position])
    }
}