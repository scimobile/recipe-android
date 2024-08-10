package com.sci.recipeandroid.feature.onboarding.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sci.recipeandroid.databinding.ItemViewDietRecipeBinding
import com.sci.recipeandroid.feature.onboarding.domain.model.DietRecipeModel
import com.sci.recipeandroid.feature.onboarding.ui.viewholder.DietRecipeViewHolder

class DietRecipeAdapter(private val onClickItem:(Int)->Unit): RecyclerView.Adapter<DietRecipeViewHolder>() {
    private var List = emptyList<DietRecipeModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DietRecipeViewHolder {
        val binding = ItemViewDietRecipeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DietRecipeViewHolder(binding,onClickItem)
    }

    override fun getItemCount(): Int {
        return List.size
    }

    override fun onBindViewHolder(holder: DietRecipeViewHolder, position: Int) {
        holder.bind(
            List[position]
        )
    }

    fun updateList(list: List<DietRecipeModel>) {
        List = list
        notifyDataSetChanged()
    }
}