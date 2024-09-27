package com.sci.recipeandroid.feature.detail.ui.viewholder.detail.detailfooter.itemviewholder

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.sci.recipeandroid.R
import com.sci.recipeandroid.databinding.ItemViewDetailCompleteMealItemBinding
import com.sci.recipeandroid.feature.detail.domain.model.CompleteMealModel

class CompleteMealItemViewHolder(
    private val binding: ItemViewDetailCompleteMealItemBinding,
    private val onClick: (Double) -> Unit,
    private val onAddToCartClick: (Double) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    private val savedBtn = binding.saveBtn
    private val recipeName = binding.recipeName
    private val ratingBar = binding.recipeRatingBar
    private val profileImg = binding.profileImg
    private val recipeImg = binding.recipeImg
    private val addToCartBtn = binding.addToCartBtn

    @SuppressLint("UseCompatLoadingForDrawables")
    fun bind(completeMealModel: CompleteMealModel) {
        recipeName.text = completeMealModel.name
        ratingBar.rating = completeMealModel.ratingPoint
        savedBtn.rippleColor = ColorStateList.valueOf(Color.WHITE)
        savedBtn.setOnClickListener {
            onClick(completeMealModel.id)
        }
        if (completeMealModel.isBookmarked) {
            savedBtn.setIconResource(R.drawable.save_fill_ic)
        }else{
            savedBtn.setIconResource(R.drawable.save_outline_ic)
        }
        addToCartBtn.setOnClickListener {
            onAddToCartClick(completeMealModel.id)
        }
        if (completeMealModel.isAddedToCart){
            addToCartBtn.isEnabled = false
            addToCartBtn.backgroundTintList = ColorStateList.valueOf(
                itemView.context.getColor(R.color.color_button_inactive)
            )
        }else{
            addToCartBtn.isEnabled = true
            addToCartBtn.backgroundTintList = ColorStateList.valueOf(
                itemView.context.getColor(R.color.color_button_active)
            )
        }
//        Glide.with(itemView.context).load(completeMeal.image).into(recipeImg)
    }
}