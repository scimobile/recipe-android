package com.sci.recipeandroid.feature.personalize.data.datasource

import com.sci.recipeandroid.feature.personalize.data.mapper.toModel
import com.sci.recipeandroid.feature.personalize.domain.service.PersonalizeService
import com.sci.recipeandroid.feature.personalize.domain.model.PersonalizeDataModel


class PersonalizeRemoteDataSource(private val service: PersonalizeService) {
    suspend fun getPersonalizeData(): PersonalizeDataModel = service.getPersonalize().toModel()
}