package com.sci.recipeandroid.feature.personalize.domain.repository

import com.sci.recipeandroid.feature.personalize.domain.model.AllergiesIngredientModel
import com.sci.recipeandroid.feature.personalize.domain.model.DietRecipeModel
import com.sci.recipeandroid.feature.personalize.domain.model.PersonalizeGoalsModel


interface PersonalizeRepository{
    suspend fun getDietRecipe(): Result<List<DietRecipeModel>>
    suspend fun getAllergiesIngredient(): Result<List<AllergiesIngredientModel>>
    suspend fun getPersonalizeGoalsList(): Result<List<PersonalizeGoalsModel>>
}