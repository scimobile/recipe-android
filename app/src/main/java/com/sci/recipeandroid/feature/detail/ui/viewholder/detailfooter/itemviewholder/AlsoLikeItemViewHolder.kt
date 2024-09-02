package com.sci.recipeandroid.feature.detail.ui.viewholder.detailfooter.itemviewholder

import androidx.recyclerview.widget.RecyclerView
import com.sci.recipeandroid.R
import com.sci.recipeandroid.databinding.DetailAlsoLikeItemViewholderBinding
import com.sci.recipeandroid.feature.detail.domain.model.AlsoLike

class AlsoLikeItemViewHolder(
    private val binding: DetailAlsoLikeItemViewholderBinding,
    private val onSavedClick:(Double) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    private val name = binding.recipeName
    private val image = binding.recipeImg
    private val profileImg = binding.profileImg
    private val savedBtn =binding.savedBtn
    fun bind(data: AlsoLike) {
        name.text = data.name
        savedBtn.setOnClickListener {
            onSavedClick(data.id)
        }
        if (data.isBookmarked){
            savedBtn.setIconResource(R.drawable.save_fill_ic)
        }else{
            savedBtn.setIconResource(R.drawable.save_outline_ic)
        }
    }
}