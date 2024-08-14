package com.sci.recipeandroid.feature.personalize.di

import com.sci.recipeandroid.feature.personalize.data.datasource.PersonalizeRemoteDataSource
import com.sci.recipeandroid.feature.personalize.data.datasource.PersonalizeRemoteDataSourceImpl
import org.koin.dsl.module

val remoteDataSourceModule = module {
    single {
        PersonalizeRemoteDataSourceImpl(get()) as PersonalizeRemoteDataSource
    }
}
