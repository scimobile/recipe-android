package com.sci.recipeandroid.feature.detail.ui.adapter.detailcenter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sci.recipeandroid.databinding.ItemViewDetailCenterBinding
import com.sci.recipeandroid.feature.detail.ui.models.DetailCenterUiModels
import com.sci.recipeandroid.feature.detail.ui.viewholder.detail.detailcenter.DetailCenterViewHolder

class DetailCenterAdapter(
    private val giveAmountResult: (Int) -> Unit,
    private val goToViewDirection: () -> Unit,
    private val goToAllNutrition: () -> Unit,
    private val addToCart:() -> Unit,
    private val onSelectUnit: (String) -> Unit
) : RecyclerView.Adapter<DetailCenterViewHolder>() {
    private var detailCenterContainerList = emptyList<DetailCenterUiModels>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailCenterViewHolder {
        ItemViewDetailCenterBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ).apply {
            return DetailCenterViewHolder(
                this,
                giveAmountResult = giveAmountResult,
                goToViewDirection = goToViewDirection,
                goToAllNutrition = goToAllNutrition,
                addToCart = addToCart,
                onSelectUnit = onSelectUnit
            )
        }
    }

    override fun getItemCount(): Int {
        return detailCenterContainerList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(ingredientLists: List<DetailCenterUiModels>) {
        detailCenterContainerList = ingredientLists
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: DetailCenterViewHolder, position: Int) {
        holder.bind(detailCenterContainerList[position])
    }
}