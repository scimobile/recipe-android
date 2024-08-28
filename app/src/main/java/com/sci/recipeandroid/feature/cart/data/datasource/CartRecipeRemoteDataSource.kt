package com.sci.recipeandroid.feature.cart.data.datasource

import com.sci.recipeandroid.feature.cart.data.model.CartRecipeResponse

interface CartRecipeRemoteDataSource {
    suspend fun getAddedRecipeList(): Result<List<CartRecipeResponse>>
    suspend fun updateIngredientCheckedStatus(ingredientId: String, checked: Boolean): Result<List<CartRecipeResponse>>
}