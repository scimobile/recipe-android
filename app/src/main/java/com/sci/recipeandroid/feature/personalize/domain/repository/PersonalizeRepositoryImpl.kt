package com.sci.recipeandroid.feature.personalize.domain.repository

import com.sci.recipeandroid.feature.personalize.data.datasource.PersonalizeRemoteDataSource
import com.sci.recipeandroid.feature.personalize.domain.model.AllergiesIngredientModel
import com.sci.recipeandroid.feature.personalize.domain.model.DietRecipeModel
import com.sci.recipeandroid.feature.personalize.domain.model.PersonalizeGoalsModel


class PersonalizeRepositoryImpl(private val remoteDatasource: PersonalizeRemoteDataSource) :PersonalizeRepository{
    override suspend fun getDietRecipe(): Result<List<DietRecipeModel>> = remoteDatasource.getDietRecipeList()
    override suspend fun getAllergiesIngredient(): Result<List<AllergiesIngredientModel>> = remoteDatasource.getAllergiesIngredient()
    override suspend fun getPersonalizeGoalsList(): Result<List<PersonalizeGoalsModel>> = remoteDatasource.getPersonalizeGoalsList()
}