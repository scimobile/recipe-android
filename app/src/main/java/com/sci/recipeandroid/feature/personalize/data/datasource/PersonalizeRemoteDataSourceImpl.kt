package com.sci.recipeandroid.feature.personalize.data.datasource

import com.sci.recipeandroid.feature.personalize.data.mapper.toDomainModel
import com.sci.recipeandroid.feature.personalize.domain.service.PersonalizeService
import com.sci.recipeandroid.feature.personalize.domain.model.PersonalizeDataModel


class PersonalizeRemoteDataSourceImpl(private val service: PersonalizeService): PersonalizeRemoteDataSource {
    override suspend fun getPersonalizeData(): Result<PersonalizeDataModel> = service.getPersonalize().map { it.toDomainModel() }
}