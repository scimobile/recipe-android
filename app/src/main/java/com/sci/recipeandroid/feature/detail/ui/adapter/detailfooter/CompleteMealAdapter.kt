package com.sci.recipeandroid.feature.detail.ui.adapter.detailfooter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sci.recipeandroid.databinding.DetailCompleteMealItemViewholderBinding
import com.sci.recipeandroid.feature.detail.domain.model.CompleteMeal
import com.sci.recipeandroid.feature.detail.domain.model.DetailFooterItem
import com.sci.recipeandroid.feature.detail.ui.viewholder.detailfooter.DetailCompleteMealViewHolder
import com.sci.recipeandroid.feature.detail.ui.viewholder.detailfooter.itemviewholder.CompleteMealItemViewHolder

class CompleteMealAdapter(
    private val onClick: (Double) -> Unit
) : RecyclerView.Adapter<CompleteMealItemViewHolder>() {
    private var completeMealList = emptyList<CompleteMeal>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompleteMealItemViewHolder {
        DetailCompleteMealItemViewholderBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ).apply {
            return CompleteMealItemViewHolder(
                this,
                onClick = onClick
            )
        }
    }

    override fun getItemCount(): Int {
        return completeMealList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(productLists: List<CompleteMeal>) {
        completeMealList = productLists
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: CompleteMealItemViewHolder, position: Int) {
        holder.bind(completeMealList[position])
    }
}