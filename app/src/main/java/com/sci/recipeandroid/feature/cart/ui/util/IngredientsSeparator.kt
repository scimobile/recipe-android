package com.sci.recipeandroid.feature.cart.ui.util

import com.sci.recipeandroid.feature.cart.ui.model.GroupedIngredientUiModel
import com.sci.recipeandroid.feature.cart.ui.model.RecipeUiModel

fun List<RecipeUiModel>.getGroupedIngredients(): List<GroupedIngredientUiModel> {
    // Aggregate ingredients by name and category
    val aggregatedIngredients = this
        .flatMap { it.items }
        .groupBy { it.ingredientId }
        .mapValues { entry ->
            val aggregatedAmount = entry.value.sumOf { it.amount }
            val ingredient = entry.value.first()
            ingredient.copy(amount = aggregatedAmount) // Copy the first ingredient and update the amount
        }
        .values
        .toList()

    // Filter out "Done" ingredients
    val doneIngredients = aggregatedIngredients
        .filter { it.checked }
        .toList()

    // Filter out ingredients that should not be in original categories
    val remainingIngredients = aggregatedIngredients
        .filterNot { it.checked }
        .groupBy { it.category }
        .toMutableMap()

    // Add "Done" category if there are any done ingredients
    if (doneIngredients.isNotEmpty()) {
        remainingIngredients["Done"] = doneIngredients
    }

    // Convert the map to a list of GroupedIngredientUiModel
    return remainingIngredients.map { (category, ingredients) ->
        GroupedIngredientUiModel(category, ingredients)
    }
}
