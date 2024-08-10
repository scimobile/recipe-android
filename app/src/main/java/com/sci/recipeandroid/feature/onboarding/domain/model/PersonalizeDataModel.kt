package com.sci.recipeandroid.feature.onboarding.domain.model

import com.sci.recipeandroid.feature.onboarding.data.model.response.AllergiesIngredient
import com.sci.recipeandroid.feature.onboarding.data.model.response.DietRecipe

data class PersonalizeDataModel (
    val dietRecipe:List<DietRecipeModel>,
    val allergiesIngredient:List<AllergiesIngredientModel>
)