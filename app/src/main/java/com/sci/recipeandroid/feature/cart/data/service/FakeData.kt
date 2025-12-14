package com.sci.recipeandroid.feature.cart.data.service

import com.sci.recipeandroid.feature.cart.data.model.CartIngredientResponse
import com.sci.recipeandroid.feature.cart.data.model.CartRecipeResponse

object FakeData {
    private var recipes: MutableList<CartRecipeResponse> = mutableListOf()

    init {
        recipes = mutableListOf(
            CartRecipeResponse(
                recipeId = "recipe1ID",
                title = "Cajun Spiced Cauliflower Rice",
                servingCount = 1,
                imageUrl = "",
                items = mutableListOf(
                    CartIngredientResponse(
                        name = "Pork",
                        amount = 2,
                        amountPerPerson = 2,
                        category = "Meat & Seafood",
                        imageUrl = "",
                        checked = true,
                        ingredientId = "porkID",
                        unit = "lb"
                    ),
                    CartIngredientResponse(
                        name = "Lettuce",
                        amount = 2,
                        category = "Vegetables",
                        imageUrl = "",
                        checked = false,
                        amountPerPerson = 2,
                        ingredientId = "lettuceID",
                        unit = "lb"
                    ),
                    CartIngredientResponse(
                        name = "Cauliflower",
                        amount = 2,
                        amountPerPerson = 2,
                        category = "Vegetables",
                        imageUrl = "",
                        checked = false,
                        ingredientId = "cauliflowerID",
                        unit = "lb"
                    )
                )
            ),
            CartRecipeResponse(
                recipeId = "recipe2ID",
                title = "Easy Korean Beef",
                servingCount = 2,
                imageUrl = "",
                items = mutableListOf(
                    CartIngredientResponse(
                        name = "Chili",
                        amount = 4,
                        category = "Spices & Seasonings",
                        imageUrl = "",
                        checked = false,
                        amountPerPerson = 2,
                        ingredientId = "chiliID",
                        unit = "lb"
                    ),
                    CartIngredientResponse(
                        name = "Pork",
                        amount = 4,
                        category = "Meat & Seafood",
                        imageUrl = "",
                        checked = true,
                        amountPerPerson = 2,
                        ingredientId = "porkID",
                        unit = "lb"
                    )
                )
            ),
            CartRecipeResponse(
                recipeId = "recipe3ID",
                title = "Basil Pork",
                servingCount = 2,
                imageUrl = "",
                items = mutableListOf(
                    CartIngredientResponse(
                        name = "Chicken",
                        amount = 4,
                        category = "Meat & Seafood",
                        imageUrl = "",
                        checked = true,
                        amountPerPerson = 2,
                        ingredientId = "chickenID",
                        unit = "lb"
                    ),
                    CartIngredientResponse(
                        name = "Chili",
                        amount = 4,
                        category = "Spices & Seasonings",
                        imageUrl = "",
                        checked = false,
                        amountPerPerson = 2,
                        ingredientId = "chiliID",
                        unit = "lb"
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

    fun deleteAllRecipe(){
        recipes.clear()
    }

    fun updateIngredientCheckedState(ingredientId: String, isChecked: Boolean) {
        recipes.forEach { recipe ->
            recipe.items.find { it.ingredientId == ingredientId }?.let { ingredient ->
                ingredient.checked = isChecked
            }
        }
    }

    fun updateRecipeServingSize(recipeId: String, newServingSize: Int) {
        recipes.find { it.recipeId == recipeId }?.let { recipe ->
            recipe.servingCount = newServingSize
            recipe.items.forEach { ingredient ->
                ingredient.amount = ingredient.amountPerPerson * newServingSize
            }
        }
    }

}
