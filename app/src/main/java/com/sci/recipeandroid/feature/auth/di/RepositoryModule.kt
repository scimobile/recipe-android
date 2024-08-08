package com.sci.recipeandroid.feature.auth.di

import com.sci.recipeandroid.feature.auth.domain.repository.AuthRepository
import com.sci.recipeandroid.feature.auth.domain.repository.AuthRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single {
        AuthRepositoryImpl(
            authRemoteDataSource = get(),
        ) as AuthRepository
    }
}