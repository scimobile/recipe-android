package com.sci.recipeandroid.feature.detail.ui.adapter.detailfooter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sci.recipeandroid.databinding.ItemViewDetailAlsoLikeBinding
import com.sci.recipeandroid.databinding.ItemViewDetailCompleteMealBinding
import com.sci.recipeandroid.databinding.ItemViewDetailRecipeOwnerBinding
import com.sci.recipeandroid.feature.detail.domain.model.AlsoLikeContainer
import com.sci.recipeandroid.feature.detail.domain.model.CompleteMealContainer
import com.sci.recipeandroid.feature.detail.domain.model.DetailFooterItem
import com.sci.recipeandroid.feature.detail.domain.model.RecipeOwnerContainer
import com.sci.recipeandroid.feature.detail.ui.viewholder.detail.detailfooter.DetailAlsoLikeViewHolder
import com.sci.recipeandroid.feature.detail.ui.viewholder.detail.detailfooter.DetailCompleteMealViewHolder
import com.sci.recipeandroid.feature.detail.ui.viewholder.detail.detailfooter.DetailFooterBaseViewHolder
import com.sci.recipeandroid.feature.detail.ui.viewholder.detail.detailfooter.RecipeOwnerViewHolder

class DetailFooterAdapter(
    private val onAlsoLikeClick: (Double) -> Unit,
    private val onCompleteMealClick: (Double) -> Unit,
    private val onCompleteMealAddToCartClick: (Double) -> Unit,
    private val onAlsoLikeAddToCartClick: (Double) -> Unit
) : RecyclerView.Adapter<DetailFooterBaseViewHolder>() {
    private var footerItemList = emptyList<DetailFooterItem>()
    private val RECIPE_OWNER_VIEWHOLDER = 0
    private val COMPLETE_MEAL_VIEWHOLDER = 1
    private val ALSO_LIKE_VIEWHOLDER = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailFooterBaseViewHolder {
        when (viewType) {
            RECIPE_OWNER_VIEWHOLDER -> {
                ItemViewDetailRecipeOwnerBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                ).apply {
                    return RecipeOwnerViewHolder(this)
                }
            }

            COMPLETE_MEAL_VIEWHOLDER -> {
                ItemViewDetailCompleteMealBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                ).apply {
                    return DetailCompleteMealViewHolder(
                        this,
                        onClick = onCompleteMealClick,
                        onAddToCartClick = onCompleteMealAddToCartClick
                    )
                }
            }

            ALSO_LIKE_VIEWHOLDER -> {
                ItemViewDetailAlsoLikeBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                ).apply {
                    return DetailAlsoLikeViewHolder(
                        this,
                        onClick = onAlsoLikeClick,
                        onAddToCartClick = onAlsoLikeAddToCartClick
                    )
                }
            }

            else -> {
                ItemViewDetailRecipeOwnerBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                ).apply {
                    return RecipeOwnerViewHolder(this)
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