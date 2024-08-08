package com.sci.recipeandroid.feature.auth.di

import com.sci.recipeandroid.feature.auth.ui.viewmodel.AuthOptionViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        AuthOptionViewModel(
            authRepository = get(),
            googleAuthenticator = get()
        )
    }
}