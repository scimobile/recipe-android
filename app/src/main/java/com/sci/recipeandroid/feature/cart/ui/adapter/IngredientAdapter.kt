package com.project.addtocart.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sci.recipeandroid.R
import com.sci.recipeandroid.feature.cart.domain.model.IngredientDetailModel
import com.sci.recipeandroid.feature.cart.ui.model.IngredientUiModel
import com.sci.recipeandroid.feature.cart.ui.model.RecipeUiModel

class IngredientAdapter(
    private val recipes: List<RecipeUiModel>,
    private val onCheckBoxClick: (IngredientUiModel) -> Unit,
    private val onItemClick: (IngredientUiModel) -> Unit
) : ListAdapter<IngredientUiModel, IngredientAdapter.IngredientViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<IngredientUiModel>() {
            override fun areItemsTheSame(oldItem: IngredientUiModel, newItem: IngredientUiModel): Boolean {
                // Compare unique IDs to determine if items are the same
                return oldItem.ingredientId == newItem.ingredientId
            }

            override fun areContentsTheSame(oldItem: IngredientUiModel, newItem: IngredientUiModel): Boolean {
                // Compare all fields to determine if the content of items are the same
                return oldItem == newItem
            }
        }
    }

    inner class IngredientViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val nameTextView: TextView = view.findViewById(R.id.ingredientName)
        private val quantityTextView: TextView = view.findViewById(R.id.ingredientQuantity)
        private val checkbox: CheckBox = view.findViewById(R.id.ingredientCheckBox)
        private val recipeName: TextView = view.findViewById(R.id.tv_recipe_name)

        fun bind(ingredient: IngredientUiModel) {
            nameTextView.text = ingredient.name
            quantityTextView.text = "${ingredient.amount} ${ingredient.unit}"
            checkbox.isChecked = ingredient.checked
            val ingredientToRecipeMap = createIngredientToRecipeMap(recipes)
            val recipeNames = ingredientToRecipeMap[ingredient.ingredientId] ?: emptyList()

            recipeName.text = when {
                recipeNames.size > 1 -> "Used in ${recipeNames.size} recipes"
                recipeNames.isEmpty() -> "Not used"
                else -> recipeNames[0]
            }

            checkbox.setOnCheckedChangeListener { _, isChecked ->
                onCheckBoxClick(ingredient.copy(checked = isChecked))
            }

            itemView.setOnClickListener {
                onItemClick.invoke(ingredient)
            }
        }
    }

    private fun createIngredientToRecipeMap(recipes: List<RecipeUiModel>): Map<String, List<String>> {
        return recipes.flatMap { recipe ->
            recipe.items.map { it.ingredientId to recipe.title }
        }.groupBy({ it.first }, { it.second })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart_ingredient, parent, false)
        return IngredientViewHolder(view)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
