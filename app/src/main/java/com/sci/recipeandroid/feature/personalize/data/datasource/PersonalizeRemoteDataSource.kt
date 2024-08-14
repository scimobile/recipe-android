package com.sci.recipeandroid.feature.personalize.data.datasource

import com.sci.recipeandroid.feature.personalize.domain.model.PersonalizeDataModel

interface PersonalizeRemoteDataSource {
    suspend fun getPersonalizeData(): Result<PersonalizeDataModel>
}