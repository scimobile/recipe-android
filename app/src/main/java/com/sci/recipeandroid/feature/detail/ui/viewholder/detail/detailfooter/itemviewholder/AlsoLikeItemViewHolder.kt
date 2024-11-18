package com.sci.recipeandroid.feature.detail.ui.viewholder.detail.detailfooter.itemviewholder

import android.content.res.ColorStateList
import androidx.recyclerview.widget.RecyclerView
import com.sci.recipeandroid.R
import com.sci.recipeandroid.databinding.ItemViewDetailAlsoLikeItemBinding
import com.sci.recipeandroid.feature.detail.domain.model.AlsoLikeModel

class AlsoLikeItemViewHolder(
    private val binding: ItemViewDetailAlsoLikeItemBinding,
    private val onSavedClick:(Double) -> Unit,
    private val onAddToCartClick:(Double) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    private val name = binding.recipeName
    private val image = binding.recipeImg
    private val profileImg = binding.profileImg
    private val addToCartBtn = binding.recipeAmountBtn
    private val savedBtn =binding.savedBtn
    fun bind(data: AlsoLikeModel) {
        name.text = data.name
        savedBtn.setOnClickListener {
            onSavedClick(data.id)
        }
        if (data.isBookmarked){
            savedBtn.setIconResource(R.drawable.save_fill_ic)
        }else{
            savedBtn.setIconResource(R.drawable.save_outline_ic)
        }

        addToCartBtn.setOnClickListener {
            onAddToCartClick(data.id)
        }

        if (data.isAddedToCart){
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
    }
}