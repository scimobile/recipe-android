package com.sci.recipeandroid.feature.cart.di

import com.sci.recipeandroid.feature.cart.data.datasource.CartRecipeRemoteDataSource
import com.sci.recipeandroid.feature.cart.data.datasource.FakeCartRecipeRemoteDataSource
import com.sci.recipeandroid.feature.cart.data.repository.CartRecipeRepository
import com.sci.recipeandroid.feature.cart.data.repository.CartRecipeRepositoryImpl
import com.sci.recipeandroid.feature.cart.ui.viewmodel.AddToCartViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val cartModule = module {
    single {
        FakeCartRecipeRemoteDataSource() as CartRecipeRemoteDataSource
    }

    single {
        CartRecipeRepositoryImpl(
            get()
        ) as CartRecipeRepository
    }

    viewModel {
        AddToCartViewModel(
            get(),
        )
    }
}