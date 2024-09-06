package com.sci.recipeandroid.feature.detail.ui.viewholder.detail

import android.annotation.SuppressLint
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.sci.recipeandroid.databinding.DetailHeaderViewholderBinding
import com.sci.recipeandroid.feature.detail.domain.model.DetailHeaderContainer

class DetailHeaderViewHolder(private val binding: DetailHeaderViewholderBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private val ratingBar = binding.ratingBar
    private val recipeNameCard = binding.recipeNameCard
    @SuppressLint("SetTextI18n")
    fun bind(data: DetailHeaderContainer) {
        binding.recipeTitleTv.text = "Teriyaki Chicken"
        binding.recipeOwnerName.text = data.recipeOwner
        binding.ratingCountTv.text = data.ratingCount + " Ratings"
        binding.estimatedTimeTv.text = data.estimatedTime
        binding.recipeDescriptionTv.text = data.description
        ratingBar.rating = data.ratingPoint
        if (data.isCommunityPick) {
            binding.communityPickImg.visibility = View.VISIBLE
            binding.communityPickTv.visibility = View.VISIBLE
        } else {
            binding.communityPickImg.visibility = View.GONE
            binding.communityPickTv.visibility = View.GONE
            ratingBar.updateLayoutParams<ConstraintLayout.LayoutParams> {
                endToEnd = recipeNameCard.id
                endToStart = ConstraintLayout.LayoutParams.UNSET
            }
        }
    }

}