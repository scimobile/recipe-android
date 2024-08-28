package com.sci.recipeandroid.feature.cart.data.mapper

import com.sci.recipeandroid.feature.cart.data.model.CartIngredientResponse
import com.sci.recipeandroid.feature.cart.data.model.CartRecipeResponse
import com.sci.recipeandroid.feature.cart.domain.model.IngredientDetailModel
import com.sci.recipeandroid.feature.cart.domain.model.RecipeDetailModel

fun CartRecipeResponse.toModels(): RecipeDetailModel = RecipeDetailModel(
    items = this.items.toIngredientModels(),
    title = this.title,
    recipeId = this.recipeId,
    server = this.server,
    imageUrl = this.imageUrl
)

fun CartIngredientResponse.toModels(): IngredientDetailModel = IngredientDetailModel(
    name = this.name,
    ingredientId = this.ingredientId,
    checked = this.checked,
    imageUrl = this.imageUrl,
    category = this.category,
    amount = this.amount,
    amountPerPerson = this.amountPerPerson
)

fun List<CartIngredientResponse>.toIngredientModels(): List<IngredientDetailModel> = this.map {
    it.toModels()
}

fun List<CartRecipeResponse>.toRecipeModels(): List<RecipeDetailModel> = this.map {
    it.toModels()
}

