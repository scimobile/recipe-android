package com.sci.recipeandroid.feature.cart.di

import com.sci.recipeandroid.feature.cart.data.repository.CartRecipeRepository
import com.sci.recipeandroid.feature.cart.data.repository.CartRecipeRepositoryImpl
import org.koin.dsl.module

val cartRecipeRepositoryModule = module {
    single {
        CartRecipeRepositoryImpl(
            get()
        ) as CartRecipeRepository
    }
}