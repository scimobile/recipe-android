package com.sci.recipeandroid.feature.onboarding.di

import com.sci.recipeandroid.feature.onboarding.data.datasource.PersonalizeRemoteDataSource
import org.koin.dsl.module

val repositoryModule = module {
    single {
        PersonalizeRemoteDataSource(get())
    }
}