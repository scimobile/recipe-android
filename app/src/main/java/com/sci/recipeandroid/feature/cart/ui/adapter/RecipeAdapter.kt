package com.project.addtocart.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.sci.recipeandroid.R
import com.sci.recipeandroid.feature.cart.ui.model.RecipeUiModel

class RecipeAdapter(
    private val onItemDelete: (RecipeUiModel) -> Unit,
    private val onServingSizeChange: (RecipeUiModel) -> Unit
) : ListAdapter<RecipeUiModel, RecipeAdapter.RecipeViewHolder>(RecipeDiffCallback()) {
    
    inner class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val titleTextView: TextView = view.findViewById(R.id.recipeTitle)
        private val imageView: ImageView = view.findViewById(R.id.recipeImage)
        private val serverCount: AutoCompleteTextView = view.findViewById(R.id.edt_server_dropdown)
        private val deleteRecipe: ImageView = view.findViewById(R.id.btn_delete_recipe)

        private val adapter = ArrayAdapter(
            itemView.context,
            R.layout.item_cart_serving,
            itemView.resources.getStringArray(R.array.server_counts)
        )

        init {
            serverCount.setAdapter(adapter)
            serverCount.setOnDismissListener {
                serverCount.clearFocus()
            }
        }

        fun bind(recipe: RecipeUiModel) {
            titleTextView.text = recipe.title

            if (serverCount.text.toString() != recipe.servingCount.toString()) {
                serverCount.setText(recipe.servingCount.toString(), false)
            }

            // Show dropdown on focus, but manage dismiss on focus loss
            serverCount.setOnItemClickListener { _, _, position, _ ->
                val newServingSize = position + 1
                if (newServingSize != recipe.servingCount) {
                    val updatedRecipe = recipe.copy(servingCount = newServingSize)
                    onServingSizeChange(updatedRecipe)
                }
                serverCount.dismissDropDown()
            }

            // Show dropdown only when focused
            serverCount.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    serverCount.showDropDown()
                } else {
                    serverCount.dismissDropDown()
                }
            }

            deleteRecipe.setOnClickListener {
                MaterialAlertDialogBuilder(itemView.context, R.style.ThemeOverlay_App_MaterialAlertDialog)
                    .setTitle("Are You Sure?")
                    .setMessage("This recipe and its ingredients will be removed from your grocery list.")
                    .setNegativeButton("CANCEL") { dialog, which ->

                    }
                    .setPositiveButton("YES") { dialog, which ->
                        onItemDelete(recipe)
                    }
                    .show()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class RecipeDiffCallback : DiffUtil.ItemCallback<RecipeUiModel>() {
    override fun areItemsTheSame(oldItem: RecipeUiModel, newItem: RecipeUiModel): Boolean {
        return oldItem.recipeId == newItem.recipeId
    }

    override fun areContentsTheSame(oldItem: RecipeUiModel, newItem: RecipeUiModel): Boolean {
        return oldItem == newItem
    }

}
