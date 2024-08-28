package com.sci.recipeandroid.feature.cart.data.datasource

import com.sci.recipeandroid.feature.cart.data.model.CartIngredientResponse
import com.sci.recipeandroid.feature.cart.data.model.CartRecipeResponse

class FakeCartRecipeRemoteDataSource(): CartRecipeRemoteDataSource {

    override suspend fun getAddedRecipeList(): Result<List<CartRecipeResponse>> {
        return try {
            val recipes = FakeDatabase.getRecipes()
            Result.success(recipes)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateIngredientCheckedStatus(
        ingredientId: String,
        checked: Boolean
    ): Result<List<CartRecipeResponse>> {
        return try {
            val result = FakeDatabase.updateIngredientCheckedState(ingredientId, checked)
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}