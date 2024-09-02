package com.sci.recipeandroid.feature.detail.ui.adapter.detailcenter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sci.recipeandroid.databinding.DetailCenterViewholderBinding
import com.sci.recipeandroid.feature.detail.domain.model.DetailCenterContainer
import com.sci.recipeandroid.feature.detail.domain.model.Ingredients
import com.sci.recipeandroid.feature.detail.ui.viewholder.detailcenter.DetailCenterViewHolder

class DetailCenterAdapter(
    private val giveAmountResult: (Int) -> Unit,
    private val goToViewDirection: () -> Unit,
    private val goToAllNutrition: () -> Unit
) : RecyclerView.Adapter<DetailCenterViewHolder>() {
    private var detailCenterContainerList = emptyList<DetailCenterContainer>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailCenterViewHolder {
        DetailCenterViewholderBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ).apply {
            return DetailCenterViewHolder(
                this,
                giveAmountResult = giveAmountResult,
                goToViewDirection = goToViewDirection,
                goToAllNutrition = goToAllNutrition
            )
        }
    }

    override fun getItemCount(): Int {
        return detailCenterContainerList.size
    }


    @SuppressLint("NotifyDataSetChanged")
    fun updateList(ingredientLists: List<DetailCenterContainer>) {
        detailCenterContainerList = ingredientLists
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateIngredientList(ingredientLists: List<Ingredients>){
        detailCenterContainerList[0].ingredients = ingredientLists
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: DetailCenterViewHolder, position: Int) {
        holder.bind(detailCenterContainerList[position])
    }
}