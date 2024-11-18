package com.sci.recipeandroid.feature.detail.ui.adapter.detailfooter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sci.recipeandroid.databinding.ItemViewDetailAlsoLikeItemBinding
import com.sci.recipeandroid.feature.detail.domain.model.AlsoLikeModel
import com.sci.recipeandroid.feature.detail.ui.viewholder.detail.detailfooter.itemviewholder.AlsoLikeItemViewHolder


class AlsoLikeAdapter(
    private val onSavedClick: (Double) -> Unit,
    private val onAddToCartClick: (Double) -> Unit
) : RecyclerView.Adapter<AlsoLikeItemViewHolder>() {
    private var productList = emptyList<AlsoLikeModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlsoLikeItemViewHolder {
        ItemViewDetailAlsoLikeItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ).apply {
            return AlsoLikeItemViewHolder(
                this,
                onSavedClick = onSavedClick,
                onAddToCartClick = onAddToCartClick
            )
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(productLists: List<AlsoLikeModel>) {
        productList = productLists
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: AlsoLikeItemViewHolder, position: Int) {
        holder.bind(productList[position])
    }
}

