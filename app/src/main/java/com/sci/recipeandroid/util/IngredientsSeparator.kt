package com.sci.recipeandroid.util

import com.sci.recipeandroid.feature.cart.domain.model.IngredientDetailModel
import com.sci.recipeandroid.feature.cart.domain.model.RecipeDetailModel
import com.sci.recipeandroid.feature.cart.ui.model.IngredientUiModel
import com.sci.recipeandroid.feature.cart.ui.model.RecipeUiModel

class IngredientsSeparator {

    fun getGroupedIngredients(
        recipes: List<RecipeUiModel>
    ): Pair<List<RecipeUiModel>, Map<String, List<IngredientUiModel>>> {

        // Aggregate ingredients by name and category
        val aggregatedIngredients = recipes
            .flatMap { it.items }
            .groupBy { Pair(it.name, it.category) }
            .mapValues { entry ->
                val aggregatedAmount = entry.value.sumOf { it.amount }
                entry.value.first().copy(amount = aggregatedAmount) // Copy the first ingredient and update the amount
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

//        Vegetables=[IngredientDetailModel(
//             ingredientId=lettuce,
//             name=Lettuce, amount=2,
//             imageUrl=, checked=false,
//             amountPerPerson=2,
//             category=Vegetables
//         )]
        
        return Pair(recipes, remainingIngredients)

    }

}