package com.sci.recipeandroid.feature.cart.domain.repository


import com.sci.recipeandroid.feature.cart.domain.model.RecipeDetailModel

interface CartRecipeRepository {

    suspend fun getAddedRecipeList(): Result<List<RecipeDetailModel>>
    suspend fun updateIngredientCheckedStatus(
        ingredientId: String,
        checked: Boolean
    ): Result<List<RecipeDetailModel>>
}