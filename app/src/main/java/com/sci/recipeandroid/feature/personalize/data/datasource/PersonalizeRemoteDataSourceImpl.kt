package com.sci.recipeandroid.feature.personalize.data.datasource

import com.sci.recipeandroid.feature.personalize.data.mapper.toDomainModel
import com.sci.recipeandroid.feature.personalize.data.mapper.toGoalsDomainModel
import com.sci.recipeandroid.feature.personalize.data.service.PersonalizeService
import com.sci.recipeandroid.feature.personalize.domain.model.PersonalizeDataModel
import com.sci.recipeandroid.feature.personalize.domain.model.PersonalizeGoalsModel


class PersonalizeRemoteDataSourceImpl(private val service: PersonalizeService): PersonalizeRemoteDataSource {
    override suspend fun getPersonalizeData(): Result<PersonalizeDataModel> = service.getPersonalize().map { it.toDomainModel() }
    override suspend fun getPersonalizeGoalsList(): Result<List<PersonalizeGoalsModel>> = service.getPersonalize().map { it.toGoalsDomainModel() }
}