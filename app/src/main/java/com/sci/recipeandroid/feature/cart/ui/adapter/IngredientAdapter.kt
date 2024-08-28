package com.project.addtocart.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sci.recipeandroid.R
import com.sci.recipeandroid.feature.cart.domain.model.IngredientDetailModel
import com.sci.recipeandroid.feature.cart.ui.model.IngredientUiModel

class IngredientAdapter(
    private val ingredientToRecipeMap: Map<String, List<String>>,
) : RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder>() {

    lateinit var onCheckBoxClick: ((IngredientUiModel) -> Unit)

    private var ingredients: List<IngredientUiModel> = emptyList()
    fun submitData(ingredients: List<IngredientUiModel>){
        this.ingredients = ingredients
        notifyDataSetChanged()
    }

    inner class IngredientViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
            
        private val nameTextView: TextView = view.findViewById(R.id.ingredientName)
        private val quantityTextView: TextView = view.findViewById(R.id.ingredientQuantity)
        private val checkbox: CheckBox = view.findViewById(R.id.ingredientCheckBox)
        private val recipeName: TextView = view.findViewById(R.id.tv_recipe_name)

        fun bind(ingredient: IngredientUiModel) {
            nameTextView.text = ingredient.name
            quantityTextView.text = ingredient.amount.toString()
            checkbox.isChecked = ingredient.checked
            val recipeNames = ingredientToRecipeMap[ingredient.ingredientId] ?: emptyList()

            recipeName.text = when {
                recipeNames.size > 1 ->{
                    "Used in ${recipeNames.size} recipes"
                }
                else -> recipeNames[0]
            }

            checkbox.setOnCheckedChangeListener { _, isChecked ->
                onCheckBoxClick(ingredient.copy(checked = isChecked))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart_ingredient, parent, false)
        return IngredientViewHolder(view)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        holder.bind(ingredients[position])
    }

    override fun getItemCount(): Int = ingredients.size

}
