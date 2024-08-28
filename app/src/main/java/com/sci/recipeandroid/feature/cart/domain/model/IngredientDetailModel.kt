package com.sci.recipeandroid.feature.cart.domain.model

data class IngredientDetailModel(
    val ingredientId: String,
    val name: String,
    val amount: Int,
    val imageUrl: String,
    var checked: Boolean,
    val amountPerPerson: Int,
    val category: String
)
