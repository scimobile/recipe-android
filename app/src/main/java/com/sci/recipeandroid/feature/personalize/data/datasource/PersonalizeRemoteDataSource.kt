package com.sci.recipeandroid.feature.personalize.data.datasource

import com.sci.recipeandroid.feature.personalize.domain.model.PersonalizeDataModel
import com.sci.recipeandroid.feature.personalize.domain.model.PersonalizeGoalsModel

interface PersonalizeRemoteDataSource {
    suspend fun getPersonalizeData(): Result<PersonalizeDataModel>
    suspend fun getPersonalizeGoalsList(): Result<List<PersonalizeGoalsModel>>
}