package com.sci.recipeandroid.feature.cart.ui.model

data class IngredientUiModel(
    val ingredientId: String,
    val name: String,
    val amount: Int,
    val imageUrl: String,
    var checked: Boolean,
    val amountPerPerson: Int,
    val category: String,
    val unit: String
)
