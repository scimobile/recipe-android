package com.sci.recipeandroid.feature.personalize.di

import com.sci.recipeandroid.feature.personalize.data.datasource.PersonalizeRemoteDataSource
import com.sci.recipeandroid.feature.personalize.domain.repository.PersonalizeRepository
import com.sci.recipeandroid.feature.personalize.domain.repository.PersonalizeRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single {
        PersonalizeRepositoryImpl(get()) as PersonalizeRepository
    }
}