package com.sci.recipeandroid.feature.cart.data.datasource

import com.sci.recipeandroid.feature.cart.data.model.CartRecipeResponse
import com.sci.recipeandroid.feature.cart.data.service.FakeData

class FakeCartRecipeRemoteDataSource(): CartRecipeRemoteDataSource {

    override suspend fun getCartRecipeList(): Result<List<CartRecipeResponse>> {
        return try {
            val recipes = FakeData.getRecipes()
            Result.success(recipes)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateIngredientCheckedStatus(
        ingredientId: String,
        checked: Boolean
    ): Result<Unit> {
        return try {
            FakeData.updateIngredientCheckedState(ingredientId, checked)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deleteRecipe(recipeId: String): Result<Unit> {
        return try {
            FakeData.deleteRecipe(recipeId = recipeId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }

    }

    override suspend fun deleteAllRecipe(): Result<Unit> {
        return try {
            FakeData.deleteAllRecipe()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateRecipeServingSize(
        recipeId: String,
        newServingSize: Int
    ): Result<Unit> {
        return try {
            FakeData.updateRecipeServingSize(recipeId, newServingSize)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}