package com.sci.recipeandroid.feature.detail.ui.adapter.detailcenter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sci.recipeandroid.databinding.DetailCenterViewholderBinding
import com.sci.recipeandroid.feature.detail.domain.model.DetailCenterContainer
import com.sci.recipeandroid.feature.detail.ui.viewholder.detailcenter.DetailCenterViewHolder

class DetailCenterAdapter(val onClick:(Int)-> Unit) : RecyclerView.Adapter<DetailCenterViewHolder>() {
    private var ingredientList = emptyList<DetailCenterContainer>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailCenterViewHolder {
        DetailCenterViewholderBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ).apply {
            return DetailCenterViewHolder(this)
        }
    }

    override fun getItemCount(): Int {
        return ingredientList.size
    }


    @SuppressLint("NotifyDataSetChanged")
    fun updateList(ingredientLists: List<DetailCenterContainer>) {
        ingredientList = ingredientLists
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: DetailCenterViewHolder, position: Int) {
        holder.bind(ingredientList[position])
    }
}