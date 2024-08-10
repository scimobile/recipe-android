package com.sci.recipeandroid.feature.onboarding.data.datasource

import com.sci.recipeandroid.feature.onboarding.data.mapper.toModel
import com.sci.recipeandroid.feature.onboarding.data.service.PersonalizeService
import com.sci.recipeandroid.feature.onboarding.domain.model.AllergiesIngredientModel
import com.sci.recipeandroid.feature.onboarding.domain.model.DietRecipeModel
import com.sci.recipeandroid.feature.onboarding.domain.model.PersonalizeDataModel


class PersonalizeRemoteDataSource(private val service: PersonalizeService) {
    suspend fun getPersonalizeData(): PersonalizeDataModel = service.getPersonalize().toModel()
}