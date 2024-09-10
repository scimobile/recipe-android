package com.sci.recipeandroid.feature.detail.ui.viewholder

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.sci.recipeandroid.R
import com.sci.recipeandroid.databinding.DirectionViewholderBinding
import com.sci.recipeandroid.feature.detail.domain.model.DirectionModel

class DirectionViewHolder(
    private val binding: DirectionViewholderBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(directionModel: DirectionModel,isLastItem: Boolean) {
        binding.stepTv.text = directionModel.name
        binding.descriptionTv.text = directionModel.description
        if (directionModel.image.isEmpty()) {
            binding.directionImg.visibility = View.GONE
        } else {
            binding.directionImg.visibility =View.VISIBLE
            Glide.with(binding.directionImg.context)
                .load(directionModel.image)
                .placeholder(R.color.black)
                .error(R.color.color_input_layout_error)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .apply(RequestOptions().override(Target.SIZE_ORIGINAL))
                .into(binding.directionImg)
        }
        if (isLastItem){
            binding.dashLine.visibility = View.GONE
        }else{
            binding.dashLine.visibility = View.VISIBLE
        }
    }
}