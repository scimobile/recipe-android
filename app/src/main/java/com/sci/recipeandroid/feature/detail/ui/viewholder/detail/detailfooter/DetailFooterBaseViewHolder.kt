package com.sci.recipeandroid.feature.detail.ui.viewholder.detail.detailfooter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.sci.recipeandroid.feature.detail.domain.model.DetailFooterItem

abstract class DetailFooterBaseViewHolder(
    binding: ViewBinding
):RecyclerView.ViewHolder(binding.root){
    abstract fun bind(data: DetailFooterItem)
}