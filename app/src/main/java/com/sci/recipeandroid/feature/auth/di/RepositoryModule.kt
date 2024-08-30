package com.sci.recipeandroid.feature.auth.di

import com.sci.recipeandroid.feature.auth.domain.repository.AuthRepository
import com.sci.recipeandroid.feature.auth.domain.repository.AuthRepositoryImpl
import com.sci.recipeandroid.feature.detail.domain.repository.DetailRepo
import com.sci.recipeandroid.feature.detail.domain.repository.DetailRepoImpl
import org.koin.dsl.module

val repositoryModule = module {
    single {
        AuthRepositoryImpl(
            authRemoteDataSource = get(),
            authLocalDataSource = get()
        ) as AuthRepository
    }
    single {
        DetailRepoImpl(
            detailRemoteDataSource = get()
        ) as DetailRepo
    }
}