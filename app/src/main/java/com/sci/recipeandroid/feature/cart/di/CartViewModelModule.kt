package com.sci.recipeandroid.feature.cart.di

import com.sci.recipeandroid.feature.cart.ui.viewmodel.AddToCartViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val cartViewModelModule = module {
    viewModel {
        AddToCartViewModel(
            get(),
        )
    }
}