package com.sci.recipeandroid.feature.detail.ui.adapter.detailfooter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sci.recipeandroid.databinding.DetailAlsoLikeItemViewholderBinding
import com.sci.recipeandroid.databinding.DetailAlsoLikeViewholderBinding
import com.sci.recipeandroid.databinding.DetailCompleteMealViewholderBinding
import com.sci.recipeandroid.databinding.DetailRecipeOwnerViewholderBinding
import com.sci.recipeandroid.feature.detail.domain.model.AlsoLikeContainer
import com.sci.recipeandroid.feature.detail.domain.model.CompleteMealContainer
import com.sci.recipeandroid.feature.detail.domain.model.DetailFooterItem
import com.sci.recipeandroid.feature.detail.domain.model.RecipeOwnerContainer
import com.sci.recipeandroid.feature.detail.ui.viewholder.detailfooter.DetailAlsoLikeViewHolder
import com.sci.recipeandroid.feature.detail.ui.viewholder.detailfooter.DetailCompleteMealViewHolder
import com.sci.recipeandroid.feature.detail.ui.viewholder.detailfooter.DetailFooterBaseViewHolder
import com.sci.recipeandroid.feature.detail.ui.viewholder.detailfooter.RecipeOwnerViewHolder

class DetailFooterAdapter(val onClick: (Int) -> Unit) :
    RecyclerView.Adapter<DetailFooterBaseViewHolder>() {
    private var footerItemList = emptyList<DetailFooterItem>()
    private val RECIPE_OWNER_VIEWHOLDER = 0
    private val COMPLETE_MEAL_VIEWHOLDER = 1
    private val ALSO_LIKE_VIEWHOLDER = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailFooterBaseViewHolder {
        when (viewType) {
            RECIPE_OWNER_VIEWHOLDER -> {
                DetailRecipeOwnerViewholderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                ).apply {
                    return RecipeOwnerViewHolder(this)
                }
            }

            COMPLETE_MEAL_VIEWHOLDER -> {
                DetailCompleteMealViewholderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                ).apply {
                    return DetailCompleteMealViewHolder(this)
                }
            }

            ALSO_LIKE_VIEWHOLDER -> {
                DetailAlsoLikeViewholderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                ).apply {
                    return DetailAlsoLikeViewHolder(this)
                }
            }

            else -> {
                DetailAlsoLikeViewholderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                ).apply {
                    return DetailAlsoLikeViewHolder(this)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return footerItemList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(footerLists: List<DetailFooterItem>) {
        footerItemList = footerLists
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when (footerItemList[position]) {
            is RecipeOwnerContainer -> RECIPE_OWNER_VIEWHOLDER
            is CompleteMealContainer -> COMPLETE_MEAL_VIEWHOLDER
            is AlsoLikeContainer -> ALSO_LIKE_VIEWHOLDER
            else -> RECIPE_OWNER_VIEWHOLDER
        }
    }

    override fun onBindViewHolder(holder: DetailFooterBaseViewHolder, position: Int) {
        holder.bind(footerItemList[position])
    }

}