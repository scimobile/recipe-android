package com.sci.recipeandroid.feature.personalize.data.datasource

import com.sci.recipeandroid.feature.personalize.domain.model.AllergiesIngredientModel
import com.sci.recipeandroid.feature.personalize.domain.model.DietRecipeModel
import com.sci.recipeandroid.feature.personalize.domain.model.PersonalizeGoalsModel

interface PersonalizeRemoteDataSource {
    suspend fun getDietRecipeList(): Result<List<DietRecipeModel>>
    suspend fun getAllergiesIngredient(): Result<List<AllergiesIngredientModel>>
    suspend fun getPersonalizeGoalsList(): Result<List<PersonalizeGoalsModel>>
}