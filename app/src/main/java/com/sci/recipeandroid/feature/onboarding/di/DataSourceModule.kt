package com.sci.recipeandroid.feature.onboarding.di

import com.sci.recipeandroid.feature.onboarding.data.datasource.PersonalizeRemoteDataSource
import org.koin.dsl.module

val remoteDataSourceModule = module {
    single {
        PersonalizeRemoteDataSource(get())
    }
}
