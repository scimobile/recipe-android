package com.sci.recipeandroid.feature.personalize.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sci.recipeandroid.databinding.ItemViewAllergiesIngredientBinding
import com.sci.recipeandroid.feature.personalize.ui.model.AllergiesIngredientUiModel
import com.sci.recipeandroid.feature.personalize.ui.viewholder.AllergiesIngredientViewHolder

class AllergiesIngredientAdapter(
    private val onClickItem: (String?) -> Unit
) : RecyclerView.Adapter<AllergiesIngredientViewHolder>() {

    private var list: List<AllergiesIngredientUiModel> = emptyList()

    fun updateList(items: List<AllergiesIngredientUiModel>) {
        list = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllergiesIngredientViewHolder {
        val binding = ItemViewAllergiesIngredientBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AllergiesIngredientViewHolder(binding, onClickItem)
    }

    override fun onBindViewHolder(holder: AllergiesIngredientViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}