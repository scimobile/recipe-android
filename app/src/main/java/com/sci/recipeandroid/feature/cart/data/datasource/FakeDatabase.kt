package com.sci.recipeandroid.feature.cart.data.datasource

import com.sci.recipeandroid.feature.cart.data.model.CartIngredientResponse
import com.sci.recipeandroid.feature.cart.data.model.CartRecipeResponse

object FakeDatabase {
    private var recipes: MutableList<CartRecipeResponse> = mutableListOf()

    init {
        recipes = mutableListOf(
            CartRecipeResponse(
                recipeId = "recipe1",
                title = "Cajun Spiced Cauliflower Rice",
                server = 1,
                imageUrl = "",
                items = mutableListOf(
                    CartIngredientResponse(
                        name = "Pork",
                        amount = 2,
                        amountPerPerson = 2,
                        category = "Meat & Seafood",
                        imageUrl = "",
                        checked = false,
                        ingredientId = "pork"
                    ),
                    CartIngredientResponse(
                        name = "Lettuce",
                        amount = 2,
                        category = "Vegetables",
                        imageUrl = "",
                        checked = false,
                        amountPerPerson = 2,
                        ingredientId = "lettuce"
                    )
                )
            ),
            CartRecipeResponse(
                recipeId = "recipe2",
                title = "Easy Korean Beef",
                server = 2,
                imageUrl = "",
                items = mutableListOf(
                    CartIngredientResponse(
                        name = "Chili",
                        amount = 4,
                        category = "Spices & Seasonings",
                        imageUrl = "",
                        checked = false,
                        amountPerPerson = 2,
                        ingredientId = "chili"
                    ),
                    CartIngredientResponse(
                        name = "Pork",
                        amount = 4,
                        category = "Meat & Seafood",
                        imageUrl = "",
                        checked = false,
                        amountPerPerson = 2,
                        ingredientId = "pork"
                    )
                )
            ),
            CartRecipeResponse(
                recipeId = "recipe3",
                title = "Basil Pork",
                server = 2,
                imageUrl = "",
                items = mutableListOf(
                    CartIngredientResponse(
                        name = "Chicken",
                        amount = 4,
                        category = "Meat & Seafood",
                        imageUrl = "",
                        checked = true,
                        amountPerPerson = 2,
                        ingredientId = "chicken"
                    ),
                    CartIngredientResponse(
                        name = "Chili",
                        amount = 4,
                        category = "Spices & Seasonings",
                        imageUrl = "",
                        checked = false,
                        amountPerPerson = 2,
                        ingredientId = "chili"
                    )
                )
            )
        )
    }
    
    fun getRecipes(): List<CartRecipeResponse> {
        return recipes
    }

    fun deleteRecipe(recipeId: String) {
        recipes.removeAll { it.recipeId == recipeId }
    }

    // Function to update the checked state of an ingredient by its ID
    fun updateIngredientCheckedState(ingredientId: String, isChecked: Boolean): List<CartRecipeResponse> {
        recipes.forEach { recipe ->
            recipe.items.find { it.ingredientId == ingredientId }?.let { ingredient ->
                ingredient.checked = isChecked
            }
        }
        return recipes
    }

}
