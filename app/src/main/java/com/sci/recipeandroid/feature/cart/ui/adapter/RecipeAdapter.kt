package com.project.addtocart.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sci.recipeandroid.R
import com.sci.recipeandroid.feature.cart.domain.model.RecipeDetailModel
import com.sci.recipeandroid.feature.cart.ui.model.RecipeUiModel

class RecipeAdapter(private val recipes: List<RecipeUiModel>) :
    RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    private val dropdownItems = (1..14).map { it.toString() }

    inner class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val titleTextView: TextView = view.findViewById(R.id.recipeTitle)
        private val imageView: ImageView = view.findViewById(R.id.recipeImage)
        private val serverCount: AutoCompleteTextView = view.findViewById(R.id.edt_server_dropdown)

        fun bind(recipe: RecipeUiModel) {
            titleTextView.text = recipe.title
            // Set up the dropdown adapter
            val adapter = ArrayAdapter(
                itemView.context,
                R.layout.item_cart_serving,
                dropdownItems
            )
            serverCount.setAdapter(adapter)

            // Optionally, set the default selection if needed
            serverCount.setText("${recipe.server}", false) // Default to "1"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipes[position])
    }

    override fun getItemCount(): Int = recipes.size
}
