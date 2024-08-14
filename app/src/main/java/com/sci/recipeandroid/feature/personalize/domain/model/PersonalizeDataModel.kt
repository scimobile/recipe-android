package com.sci.recipeandroid.feature.personalize.domain.model

data class PersonalizeDataModel (
    val dietRecipe:List<DietRecipeModel>,
    val allergiesIngredient:List<AllergiesIngredientModel>,
    val personalizeGoals: List<PersonalizeGoalsModel>
)