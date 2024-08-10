package com.sci.recipeandroid.feature.onboarding.data.mapper

import com.sci.recipeandroid.feature.onboarding.data.model.response.PersonalizeResponse
import com.sci.recipeandroid.feature.onboarding.domain.model.AllergiesIngredientModel
import com.sci.recipeandroid.feature.onboarding.domain.model.DietRecipeModel
import com.sci.recipeandroid.feature.onboarding.domain.model.PersonalizeDataModel

fun PersonalizeResponse.toModel(): PersonalizeDataModel {
    return PersonalizeDataModel(
        dietRecipe = data.dietRecipe.map {
            DietRecipeModel(
                id = it.id,
                name = it.name.orEmpty(),
                imageUrl = it.imageUrl.orEmpty()
            )
        },
        allergiesIngredient = data.allergiesIngredient.map {
            AllergiesIngredientModel(
                id = it.id,
                name = it.name.orEmpty()
            )
        }
    )
}