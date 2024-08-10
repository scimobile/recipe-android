package com.sci.recipeandroid.feature.personalize.di

import com.sci.recipeandroid.feature.personalize.data.datasource.PersonalizeRemoteDataSource
import org.koin.dsl.module

val remoteDataSourceModule = module {
    single {
        PersonalizeRemoteDataSource(get())
    }
}
