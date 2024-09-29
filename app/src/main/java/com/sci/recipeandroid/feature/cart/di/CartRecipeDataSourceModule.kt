package com.sci.recipeandroid.feature.cart.di

import com.sci.recipeandroid.feature.cart.data.datasource.CartRecipeRemoteDataSource
import com.sci.recipeandroid.feature.cart.data.datasource.FakeCartRecipeRemoteDataSource
import org.koin.dsl.module

val cartRecipeDataSourceModule = module {
    single {
        FakeCartRecipeRemoteDataSource() as CartRecipeRemoteDataSource
    }
}