package com.sci.recipeandroid.feature.detail.ui.viewholder.detail.detailfooter.itemviewholder

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.sci.recipeandroid.R
import com.sci.recipeandroid.databinding.DetailCompleteMealItemViewholderBinding
import com.sci.recipeandroid.feature.detail.domain.model.CompleteMeal

class CompleteMealItemViewHolder(
    private val binding: DetailCompleteMealItemViewholderBinding,
    private val onClick: (Double) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    private val savedBtn = binding.saveBtn
    private val recipeName = binding.recipeName
    private val ratingBar = binding.recipeRatingBar
    private val profileImg = binding.profileImg
    private val recipeImg = binding.recipeImg

    @SuppressLint("UseCompatLoadingForDrawables")
    fun bind(completeMeal: CompleteMeal) {
        recipeName.text = completeMeal.name
        ratingBar.rating = completeMeal.ratingPoint
        savedBtn.rippleColor = ColorStateList.valueOf(Color.WHITE)
        savedBtn.setOnClickListener {
            onClick(completeMeal.id)
        }
        if (completeMeal.isBookmarked) {
            savedBtn.setIconResource(R.drawable.save_fill_ic)
        }else{
            savedBtn.setIconResource(R.drawable.save_outline_ic)
        }
//        Glide.with(itemView.context).load(completeMeal.image).into(recipeImg)
    }
}