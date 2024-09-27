package com.sci.recipeandroid.feature.detail.ui.viewholder.detail.detailfooter

import androidx.recyclerview.widget.LinearLayoutManager
import com.sci.recipeandroid.databinding.ItemViewDetailAlsoLikeBinding
import com.sci.recipeandroid.feature.detail.domain.model.AlsoLikeContainer
import com.sci.recipeandroid.feature.detail.domain.model.DetailFooterItem
import com.sci.recipeandroid.feature.detail.ui.adapter.detailfooter.AlsoLikeAdapter

class DetailAlsoLikeViewHolder(
    private val binding: ItemViewDetailAlsoLikeBinding,
    private val onClick: (Double) -> Unit,
    private val onAddToCartClick: (Double) -> Unit
) : DetailFooterBaseViewHolder(binding) {
    private val recyclerView = binding.alsoLikeRv
    private val adapter = AlsoLikeAdapter(
        onSavedClick = onClick,
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
        if (data is AlsoLikeContainer) {
            adapter.updateList(data.alsoLikeModelList)
        }
    }
}