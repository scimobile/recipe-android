package com.sci.recipeandroid.feature.cart.domain.model

data class RecipeDetailModel(
    val recipeId: String,
    val title: String,
    val imageUrl: String,
    val server: Int,
    val items: List<IngredientDetailModel>
)


