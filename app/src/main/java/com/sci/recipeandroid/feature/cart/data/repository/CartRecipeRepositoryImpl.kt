package com.sci.recipeandroid.feature.cart.data.repository

import com.sci.recipeandroid.feature.cart.data.datasource.CartRecipeRemoteDataSource
import com.sci.recipeandroid.feature.cart.data.util.toRecipeModels
import com.sci.recipeandroid.feature.cart.domain.model.RecipeDetailModel

class CartRecipeRepositoryImpl(
    private val cartRecipeRemoteDataSource: CartRecipeRemoteDataSource
): CartRecipeRepository {

    override suspend fun getAddedRecipeList(): Result<List<RecipeDetailModel>> {
        return cartRecipeRemoteDataSource.getCartRecipeList().map {
            it.toRecipeModels()
        }
    }

    override suspend fun getIngList(): Result<List<RecipeDetailModel>> {
        return cartRecipeRemoteDataSource.getCartRecipeList().map {
            it.toRecipeModels()
        }
    }

    override suspend fun updateIngredientCheckedStatus(
        ingredientId: String,
        checked: Boolean
    ): Result<Unit> {
        return cartRecipeRemoteDataSource.updateIngredientCheckedStatus(
            ingredientId,
            checked
        )
    }

    override suspend fun deleteRecipe(recipeId: String): Result<Unit> {
        return cartRecipeRemoteDataSource.deleteRecipe(recipeId)
    }

    override suspend fun deleteAllRecipe(): Result<Unit> {
        return cartRecipeRemoteDataSource.deleteAllRecipe()
    }

    override suspend fun updateServingSize(recipeId: String, newServingSize: Int): Result<Unit> {
        return cartRecipeRemoteDataSource.updateRecipeServingSize(recipeId, newServingSize)
    }
}