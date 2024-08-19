package com.sci.recipeandroid.feature.personalize.data.datasource

import com.sci.recipeandroid.feature.personalize.data.mapper.toAllergiesIngredientModel
import com.sci.recipeandroid.feature.personalize.data.mapper.toDietRecipeDomainModel
import com.sci.recipeandroid.feature.personalize.data.mapper.toGoalsDomainModel
import com.sci.recipeandroid.feature.personalize.data.service.PersonalizeService
import com.sci.recipeandroid.feature.personalize.domain.model.AllergiesIngredientModel
import com.sci.recipeandroid.feature.personalize.domain.model.DietRecipeModel
import com.sci.recipeandroid.feature.personalize.domain.model.PersonalizeGoalsModel


class PersonalizeRemoteDataSourceImpl(private val service: PersonalizeService): PersonalizeRemoteDataSource {
    override suspend fun getDietRecipeList(): Result<List<DietRecipeModel>> = service.getPersonalize().map { it.toDietRecipeDomainModel() }
    override suspend fun getAllergiesIngredient(): Result<List<AllergiesIngredientModel>> = service.getPersonalize().map { it.toAllergiesIngredientModel() }
    override suspend fun getPersonalizeGoalsList(): Result<List<PersonalizeGoalsModel>> = service.getPersonalize().map { it.toGoalsDomainModel() }
}