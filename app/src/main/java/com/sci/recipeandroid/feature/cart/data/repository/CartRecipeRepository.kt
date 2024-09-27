package com.sci.recipeandroid.feature.cart.data.repository


import com.sci.recipeandroid.feature.cart.domain.model.RecipeDetailModel
import com.sci.recipeandroid.feature.cart.ui.model.RecipeUiModel

interface CartRecipeRepository {

    suspend fun getAddedRecipeList(): Result<List<RecipeDetailModel>>
    suspend fun getIngList(): Result<List<RecipeDetailModel>>

    suspend fun updateIngredientCheckedStatus(
        ingredientId: String,
        checked: Boolean
    ): Result<Unit>
    suspend fun deleteRecipe(recipeId: String): Result<Unit>
    suspend fun deleteAllRecipe(): Result<Unit>
    suspend fun updateServingSize(recipeId: String, newServingSize: Int): Result<Unit>
}