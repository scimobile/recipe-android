package com.sci.recipeandroid.feature.cart.ui.mapper

import com.sci.recipeandroid.feature.cart.domain.model.IngredientDetailModel
import com.sci.recipeandroid.feature.cart.domain.model.RecipeDetailModel
import com.sci.recipeandroid.feature.cart.ui.model.IngredientUiModel
import com.sci.recipeandroid.feature.cart.ui.model.RecipeUiModel

fun RecipeDetailModel.toUiModels(): RecipeUiModel = RecipeUiModel(
    items = this.items.toIngredientUiModels(),
    title = this.title,
    recipeId = this.recipeId,
    server = this.server,
    imageUrl = this.imageUrl
)

fun List<IngredientDetailModel>.toIngredientUiModels(): List<IngredientUiModel> = this.map {
    it.toUiModels()
}

fun IngredientDetailModel.toUiModels(): IngredientUiModel = IngredientUiModel(
    name = this.name,
    ingredientId = this.ingredientId,
    checked = this.checked,
    imageUrl = this.imageUrl,
    category = this.category,
    amount = this.amount,
    amountPerPerson = this.amountPerPerson
)

fun List<RecipeDetailModel>.toRecipesUiModels() = this.map {
    it.toUiModels()
}


