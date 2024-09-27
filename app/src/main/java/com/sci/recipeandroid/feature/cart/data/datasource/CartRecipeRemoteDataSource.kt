package com.sci.recipeandroid.feature.cart.data.datasource

import com.sci.recipeandroid.feature.cart.data.model.CartRecipeResponse

interface CartRecipeRemoteDataSource {
    suspend fun getCartRecipeList(): Result<List<CartRecipeResponse>>
    suspend fun updateIngredientCheckedStatus(ingredientId: String, checked: Boolean): Result<Unit>
    suspend fun deleteRecipe(recipeId: String): Result<Unit>
    suspend fun deleteAllRecipe(): Result<Unit>
    suspend fun updateRecipeServingSize(recipeId: String, newServingSize: Int): Result<Unit>
}