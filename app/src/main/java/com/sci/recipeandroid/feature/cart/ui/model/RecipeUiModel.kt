package com.sci.recipeandroid.feature.cart.ui.model

data class RecipeUiModel(
    val recipeId: String,
    val title: String,
    val imageUrl: String,
    val servingCount: Int,
    val items: List<IngredientUiModel>
)