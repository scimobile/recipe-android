package com.sci.recipeandroid.feature.detail.ui.viewholder.detailfooter.itemviewholder

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.sci.recipeandroid.databinding.DetailAlsoLikeItemViewholderBinding
import com.sci.recipeandroid.feature.detail.domain.model.AlsoLike

class AlsoLikeItemViewHolder(private val binding: DetailAlsoLikeItemViewholderBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private val name = binding.recipeName
    private val image = binding.recipeImg
    private val profileImg = binding.profileImg
    fun bind(data: AlsoLike) {
        name.text = data.name
    }
}