package com.sci.recipeandroid.feature.detail.ui.viewholder.detail.detailfooter

import androidx.recyclerview.widget.LinearLayoutManager
import com.sci.recipeandroid.databinding.DetailAlsoLikeItemViewholderBinding
import com.sci.recipeandroid.databinding.DetailAlsoLikeViewholderBinding
import com.sci.recipeandroid.feature.detail.domain.model.AlsoLikeContainer
import com.sci.recipeandroid.feature.detail.domain.model.DetailFooterItem
import com.sci.recipeandroid.feature.detail.ui.adapter.detailfooter.AlsoLikeAdapter

class DetailAlsoLikeViewHolder(
    private val binding: DetailAlsoLikeViewholderBinding,
    private val onClick: (Double) -> Unit
) : DetailFooterBaseViewHolder(binding) {
    private val recyclerView = binding.alsoLikeRv
    private val adapter = AlsoLikeAdapter(
        onSavedClick = onClick
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
            adapter.updateList(data.alsoLikeList)
        }
    }
}