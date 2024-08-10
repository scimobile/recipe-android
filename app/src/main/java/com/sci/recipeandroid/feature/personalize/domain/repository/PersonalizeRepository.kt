package com.sci.recipeandroid.feature.personalize.domain.repository

import com.sci.recipeandroid.feature.personalize.data.datasource.PersonalizeRemoteDataSource
import com.sci.recipeandroid.feature.personalize.domain.model.PersonalizeDataModel


class PersonalizeRepository(private val remoteDatasource: PersonalizeRemoteDataSource) {
    suspend fun getPersonalizeData(): PersonalizeDataModel = remoteDatasource.getPersonalizeData()
}