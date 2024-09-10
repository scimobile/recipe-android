package com.sci.recipeandroid.feature.auth.di

import com.sci.recipeandroid.feature.auth.ui.viewmodel.AuthOptionViewModel
import com.sci.recipeandroid.feature.auth.ui.viewmodel.LoginViewModel
import com.sci.recipeandroid.feature.auth.ui.viewmodel.SignUpViewModel
import com.sci.recipeandroid.feature.detail.ui.viewmodel.DetailViewModel
import com.sci.recipeandroid.feature.detail.ui.viewmodel.DirectionViewModel
import com.sci.recipeandroid.feature.detail.ui.viewmodel.NutritionViewModel
import com.sci.recipeandroid.util.Validator
import com.sci.recipeandroid.util.ValidatorImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { ValidatorImpl(context = get()) as Validator }
    viewModel {
        AuthOptionViewModel(
            authRepository = get(),
            googleAuthenticator = get(),
            facebookAuthenticator = get()
        )
    }
    viewModel {
        SignUpViewModel(
            authRepository = get(),
            validator = get()
        )
    }

    viewModel {
        LoginViewModel(
            authRepository = get(),
            validator = get()
        )
    }

    viewModel {
        DetailViewModel(
            detailRepo = get()
        )
    }

    viewModel {
        NutritionViewModel(
            detailRepo = get()
        )
    }
    viewModel {
        DirectionViewModel(
            detailRepo = get()
        )
    }
}