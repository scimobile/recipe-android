package com.sci.recipeandroid.feature.personalize.domain.repository

import com.sci.recipeandroid.feature.personalize.data.datasource.PersonalizeRemoteDataSource
import com.sci.recipeandroid.feature.personalize.data.datasource.PersonalizeRemoteDataSourceImpl
import com.sci.recipeandroid.feature.personalize.domain.model.PersonalizeDataModel


interface PersonalizeRepository{
    suspend fun getPersonalizeData(): Result<PersonalizeDataModel>
}