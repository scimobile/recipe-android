package com.sci.recipeandroid.feature.detail.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sci.recipeandroid.databinding.DirectionViewholderBinding
import com.sci.recipeandroid.feature.detail.domain.model.DirectionModel
import com.sci.recipeandroid.feature.detail.ui.viewholder.DirectionViewHolder

class DirectionAdapter : RecyclerView.Adapter<DirectionViewHolder>() {
    private var directionList = emptyList<DirectionModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DirectionViewHolder {
        DirectionViewholderBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ).apply {
            return DirectionViewHolder(this)
        }
    }

    override fun getItemCount(): Int {
        return directionList.size
    }

    fun updateList(productLists: List<DirectionModel>) {
        directionList = productLists
    }


    override fun onBindViewHolder(holder: DirectionViewHolder, position: Int) {
        holder.bind(
            directionList[position],
            isLastItem = position == directionList.lastIndex
        )
    }
}