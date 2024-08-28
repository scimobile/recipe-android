package com.project.addtocart.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sci.recipeandroid.R
import com.sci.recipeandroid.feature.cart.domain.model.IngredientDetailModel
import com.sci.recipeandroid.feature.cart.domain.model.RecipeDetailModel
import com.sci.recipeandroid.feature.cart.ui.model.IngredientUiModel
import com.sci.recipeandroid.feature.cart.ui.model.RecipeUiModel
import com.sci.recipeandroid.feature.cart.ui.viewmodel.AddToCartViewModel

class GroupedIngredientAdapter(
    private val viewModel: AddToCartViewModel,
) :
    RecyclerView.Adapter<GroupedIngredientAdapter.CategoryViewHolder>() {

    private var groupedIngredients: Map<String, List<IngredientUiModel>> = emptyMap()
    private var recipes: List<RecipeUiModel> = emptyList()

    fun submitData(groupedIngredients: Map<String, List<IngredientUiModel>>, recipes: List<RecipeUiModel>) {
        this.groupedIngredients = groupedIngredients
        this.recipes = recipes
        notifyDataSetChanged()
    }

    inner class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val categoryTitleTextView: TextView = view.findViewById(R.id.categoryTitle)
        private val ingredientRecyclerView: RecyclerView = view.findViewById(R.id.ingredientRecyclerView)

        fun bind(category: String, ingredients: List<IngredientUiModel>) {
            categoryTitleTextView.text = category
            val ingredientToRecipeMap = createIngredientToRecipeMap(recipes)

            val ingredientAdapter = IngredientAdapter(ingredientToRecipeMap)

            ingredientAdapter.submitData(ingredients)
            ingredientRecyclerView.adapter = ingredientAdapter
            ingredientRecyclerView.layoutManager = LinearLayoutManager(itemView.context)

            ingredientAdapter.onCheckBoxClick = {
                viewModel.updateIngredientCheckedStatus(
                    ingredientId = it.ingredientId,
                    checked = it.checked
                )
            }
        }
    }

    private fun createIngredientToRecipeMap(recipes: List<RecipeUiModel >): Map<String, List<String>> {
        val map = mutableMapOf<String, MutableList<String>>()
        for (recipe in recipes) {
            for (ingredient in recipe.items) {
                if (map.containsKey(ingredient.ingredientId)) {
                    map[ingredient.ingredientId]?.add(recipe.title)
                } else {
                    map[ingredient.ingredientId] = mutableListOf(recipe.title)
                }
            }
        }
        //chili=[Easy Korean Beef, Basil Pork]
        return map
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart_category_group, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = groupedIngredients.keys.toList()[position]
        val ingredients = groupedIngredients[category] ?: emptyList()
        holder.bind(category, ingredients)
    }

    override fun getItemCount(): Int = groupedIngredients.size

}
