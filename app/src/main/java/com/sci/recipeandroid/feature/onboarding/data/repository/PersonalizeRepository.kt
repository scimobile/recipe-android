package com.sci.recipeandroid.feature.onboarding.data.repository

import com.sci.recipeandroid.feature.onboarding.data.datasource.PersonalizeRemoteDataSource
import com.sci.recipeandroid.feature.onboarding.data.model.response.PersonalizeData
import com.sci.recipeandroid.feature.onboarding.domain.model.AllergiesIngredientModel
import com.sci.recipeandroid.feature.onboarding.domain.model.DietRecipeModel
import com.sci.recipeandroid.feature.onboarding.domain.model.PersonalizeDataModel


class PersonalizeRepository(private val remoteDatasource: PersonalizeRemoteDataSource) {
    suspend fun getPersonalizeData():PersonalizeDataModel = remoteDatasource.getPersonalizeData()
}