package com.sci.recipeandroid.feature.auth.di

import com.sci.recipeandroid.feature.auth.data.datasource.local.AuthLocalDataSource
import com.sci.recipeandroid.feature.auth.data.datasource.local.AuthLocalDataSourceImpl
import com.sci.recipeandroid.feature.auth.data.datasource.remote.AuthRemoteDataSource
import com.sci.recipeandroid.feature.auth.data.datasource.remote.FakeAuthRemoteDataSource
import org.koin.dsl.module

val remoteDataSourceModule = module {
    single {
//        AuthRemoteDataSourceImpl(
//            googleAuthenticator = get(),
//            facebookAuthenticator = get()
//        ) as AuthRemoteDataSource
        FakeAuthRemoteDataSource() as AuthRemoteDataSource
    }
}

val localDataSourceModule = module {
    single {
        AuthLocalDataSourceImpl() as AuthLocalDataSource
    }
}