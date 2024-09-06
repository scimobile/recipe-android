package com.sci.recipeandroid.feature.detail.ui.adapter.detailfooter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sci.recipeandroid.databinding.DetailAlsoLikeItemViewholderBinding
import com.sci.recipeandroid.feature.detail.domain.model.AlsoLike
import com.sci.recipeandroid.feature.detail.ui.viewholder.detail.detailfooter.itemviewholder.AlsoLikeItemViewHolder


class AlsoLikeAdapter(private val onSavedClick: (Double) -> Unit) :
    RecyclerView.Adapter<AlsoLikeItemViewHolder>() {
    private var productList = emptyList<AlsoLike>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlsoLikeItemViewHolder {
        DetailAlsoLikeItemViewholderBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ).apply {
            return AlsoLikeItemViewHolder(
                this,
                onSavedClick = onSavedClick
            )
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(productLists: List<AlsoLike>) {
        productList = productLists
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: AlsoLikeItemViewHolder, position: Int) {
        holder.bind(productList[position])
    }
}

