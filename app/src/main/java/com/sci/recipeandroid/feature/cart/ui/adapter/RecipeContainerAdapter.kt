package com.sci.recipeandroid.feature.cart.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.project.addtocart.adapter.RecipeAdapter
import com.sci.recipeandroid.R
import com.sci.recipeandroid.feature.cart.ui.model.IngredientUiModel
import com.sci.recipeandroid.feature.cart.ui.model.RecipeContainerUiModel
import com.sci.recipeandroid.feature.cart.ui.model.RecipeUiModel


class RecipeContainerAdapter(
    private val recipeAdapter: RecipeAdapter
) : RecyclerView.Adapter<RecipeContainerAdapter.RecipeContainerViewHolder>() {

    private var recipes: List<RecipeContainerUiModel> = emptyList()

    fun submitList(recipes: List<RecipeContainerUiModel>){
        this.recipes = recipes
        notifyDataSetChanged()
    }

    inner class RecipeContainerViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val itemCount: TextView = view.findViewById(R.id.tv_item_count)
        val recipeRecycler: RecyclerView = view.findViewById(R.id.rv_recipe)
        
        init {
            recipeRecycler.adapter = recipeAdapter
        }

        fun bind(groupedRecipe: RecipeContainerUiModel) {
            recipeAdapter.submitList(groupedRecipe.recipes)
            itemCount.text = itemCount.context.getString(R.string.cart_recipe_item_count,
                groupedRecipe.recipeSize, groupedRecipe.ingredientSize
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeContainerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_cart_recipe_container, parent, false
        )
        return RecipeContainerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    override fun onBindViewHolder(holder: RecipeContainerViewHolder, position: Int) {
        holder.bind(recipes[position])
    }
}

