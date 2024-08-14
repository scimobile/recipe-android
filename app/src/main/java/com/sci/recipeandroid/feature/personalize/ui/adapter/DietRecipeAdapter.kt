package com.sci.recipeandroid.feature.personalize.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sci.recipeandroid.databinding.ItemViewDietRecipeBinding
import com.sci.recipeandroid.feature.personalize.domain.model.DietRecipeModel
import com.sci.recipeandroid.feature.personalize.ui.viewholder.DietRecipeViewHolder

class DietRecipeAdapter(private val onClickItem: (Boolean) -> Unit) : RecyclerView.Adapter<DietRecipeViewHolder>() {
    private var list = emptyList<DietRecipeModel>()
    private var selectedPosition = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DietRecipeViewHolder {
        val binding = ItemViewDietRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DietRecipeViewHolder(binding, onClickItem)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: DietRecipeViewHolder, position: Int) {
        holder.bind(list[position], position == selectedPosition)
        holder.setSelectionCallback {
            val previousPosition = selectedPosition
            selectedPosition = holder.adapterPosition
            notifyItemChanged(previousPosition)
            notifyItemChanged(selectedPosition)
            onClickItem(position == selectedPosition)
        }
    }

    fun updateList(list: List<DietRecipeModel>) {
        this.list = list
        notifyDataSetChanged()
    }
}
