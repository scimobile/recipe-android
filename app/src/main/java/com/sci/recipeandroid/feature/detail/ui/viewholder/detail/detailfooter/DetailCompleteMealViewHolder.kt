package com.sci.recipeandroid.feature.detail.ui.viewholder.detail.detailfooter

import androidx.recyclerview.widget.LinearLayoutManager
import com.sci.recipeandroid.databinding.ItemViewDetailCompleteMealBinding
import com.sci.recipeandroid.feature.detail.domain.model.CompleteMealContainer
import com.sci.recipeandroid.feature.detail.domain.model.DetailFooterItem
import com.sci.recipeandroid.feature.detail.ui.adapter.detailfooter.CompleteMealAdapter

class DetailCompleteMealViewHolder(
    private val binding: ItemViewDetailCompleteMealBinding,
    private val onClick: (Double) -> Unit,
    private val onAddToCartClick: (Double) -> Unit
) : DetailFooterBaseViewHolder(binding) {
    private val recyclerView = binding.completeMealRv
    private val adapter = CompleteMealAdapter (
        onClick = onClick,
        onAddToCartClick = onAddToCartClick
    )
    init {
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(
            binding.root.context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
    }
    override fun bind(data: DetailFooterItem) {
        if (data is CompleteMealContainer) {
            adapter.updateList(data.completeMealModelList)
        }
    }
}