package com.sci.recipeandroid.feature.detail.ui.viewholder.detailfooter

import androidx.recyclerview.widget.LinearLayoutManager
import com.sci.recipeandroid.databinding.DetailCompleteMealItemViewholderBinding
import com.sci.recipeandroid.databinding.DetailCompleteMealViewholderBinding
import com.sci.recipeandroid.feature.detail.domain.model.CompleteMealContainer
import com.sci.recipeandroid.feature.detail.domain.model.DetailFooterItem
import com.sci.recipeandroid.feature.detail.ui.adapter.detailfooter.CompleteMealAdapter

class DetailCompleteMealViewHolder(
    private val binding: DetailCompleteMealViewholderBinding
) : DetailFooterBaseViewHolder(binding) {
    private val recyclerView = binding.completeMealRv
    private val adapter = CompleteMealAdapter {}
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
            adapter.updateList(data.completeMealList)
        }
    }
}