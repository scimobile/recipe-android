package com.sci.recipeandroid.feature.detail.ui.adapter.detailfooter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sci.recipeandroid.databinding.DetailCompleteMealItemViewholderBinding
import com.sci.recipeandroid.feature.detail.domain.model.CompleteMealModel
import com.sci.recipeandroid.feature.detail.ui.viewholder.detail.detailfooter.itemviewholder.CompleteMealItemViewHolder

class CompleteMealAdapter(
    private val onClick: (Double) -> Unit
) : RecyclerView.Adapter<CompleteMealItemViewHolder>() {
    private var completeMealModelList = emptyList<CompleteMealModel>()
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
        return completeMealModelList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(productLists: List<CompleteMealModel>) {
        completeMealModelList = productLists
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: CompleteMealItemViewHolder, position: Int) {
        holder.bind(completeMealModelList[position])
    }
}