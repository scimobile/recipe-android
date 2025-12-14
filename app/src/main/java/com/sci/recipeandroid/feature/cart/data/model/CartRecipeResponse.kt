package com.sci.recipeandroid.feature.cart.data.model

data class CartRecipeResponse(
    val recipeId: String,
    val title: String,
    var servingCount: Int,
    val imageUrl: String,
    val items: List<CartIngredientResponse>
)


