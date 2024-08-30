package com.sci.recipeandroid.feature.detail.ui.viewholder.detailfooter.itemviewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sci.recipeandroid.databinding.DetailCompleteMealItemViewholderBinding
import com.sci.recipeandroid.feature.detail.domain.model.CompleteMeal

class CompleteMealItemViewHolder(private val binding: DetailCompleteMealItemViewholderBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private val imgBtn = binding.saveImgBtn
    private val recipeName = binding.recipeName
    private val ratingBar = binding.recipeRatingBar
    private val profileImg = binding.profileImg
    private val recipeImg = binding.recipeImg

    fun bind(completeMeal: CompleteMeal) {
        recipeName.text = completeMeal.name
        ratingBar.rating = completeMeal.ratingPoint
//        Glide.with(itemView.context).load(completeMeal.image).into(recipeImg)
    }
}