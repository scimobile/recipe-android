package com.sci.recipeandroid.feature.personalize.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sci.recipeandroid.databinding.ItemViewDietRecipeBinding
import com.sci.recipeandroid.feature.personalize.ui.model.DietRecipeUiModel
import com.sci.recipeandroid.feature.personalize.ui.viewholder.DietRecipeViewHolder

class DietRecipeAdapter(
    private val onClickItem: (String?) -> Unit
) : RecyclerView.Adapter<DietRecipeViewHolder>() {

    private var list: List<DietRecipeUiModel> = emptyList()

    fun updateList(items: List<DietRecipeUiModel>) {
        list = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DietRecipeViewHolder {
        val binding = ItemViewDietRecipeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DietRecipeViewHolder(binding, onClickItem)
    }

    override fun onBindViewHolder(holder: DietRecipeViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}
