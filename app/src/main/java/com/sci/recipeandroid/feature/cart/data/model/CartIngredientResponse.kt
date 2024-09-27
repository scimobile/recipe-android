package com.sci.recipeandroid.feature.cart.data.model

data class CartIngredientResponse(
    val ingredientId: String,
    val name: String,
    var amount: Int,
    val imageUrl: String,
    var checked: Boolean,
    val amountPerPerson: Int,
    val category: String,
    val unit: String
)