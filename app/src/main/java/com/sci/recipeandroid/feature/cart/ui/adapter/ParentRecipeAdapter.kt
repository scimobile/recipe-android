package com.sci.recipeandroid.feature.cart.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.addtocart.adapter.RecipeAdapter
import com.sci.recipeandroid.R
import com.sci.recipeandroid.feature.cart.domain.model.RecipeDetailModel
import com.sci.recipeandroid.feature.cart.ui.model.RecipeUiModel

class ParentRecipeAdapter(

): RecyclerView.Adapter<ParentRecipeAdapter.ParentRecipeViewHolder>() {

    private var recipes: List<RecipeUiModel> = emptyList()
    private var ingredientSize: Int = 0

    fun submitData(recipes: List<RecipeUiModel>, ingredientSize: Int) {
        this.recipes = recipes
        this.ingredientSize = ingredientSize
    }

    inner class ParentRecipeViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val itemCount: TextView = view.findViewById(R.id.tv_item_count)
        val recipeRecycler: RecyclerView = view.findViewById(R.id.rv_recipe)
        fun bind(recipes: List<RecipeUiModel>) {

            itemCount.text = itemCount.context.getString(R.string.cart_recipe_item_count,
                recipes.size, ingredientSize)

            val recipeAdapter = RecipeAdapter(recipes)
            recipeRecycler.adapter = recipeAdapter
            recipeRecycler.layoutManager = LinearLayoutManager(itemView.context,
                LinearLayoutManager.HORIZONTAL, false)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentRecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_cart_recipe_container, parent, false
        )
        return ParentRecipeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: ParentRecipeViewHolder, position: Int) {
        holder.bind(recipes)
    }
}