package com.sci.recipeandroid.feature.cart.domain.repository

import com.sci.recipeandroid.feature.cart.data.datasource.CartRecipeRemoteDataSource
import com.sci.recipeandroid.feature.cart.data.mapper.toRecipeModels
import com.sci.recipeandroid.feature.cart.domain.model.RecipeDetailModel

class CartRecipeRepositoryImpl(
    private val cartRecipeRemoteDataSource: CartRecipeRemoteDataSource
): CartRecipeRepository {

    override suspend fun getAddedRecipeList(): Result<List<RecipeDetailModel>> {
        return cartRecipeRemoteDataSource.getAddedRecipeList().map {
            it.toRecipeModels()
        }
    }

    override suspend fun updateIngredientCheckedStatus(
        ingredientId: String,
        checked: Boolean
    ): Result<List<RecipeDetailModel>> {
        return cartRecipeRemoteDataSource.updateIngredientCheckedStatus(
            ingredientId,
            checked
        ).map {
            it.toRecipeModels()
        }
    }
}