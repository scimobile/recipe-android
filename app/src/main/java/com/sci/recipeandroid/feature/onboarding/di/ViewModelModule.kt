package com.sci.recipeandroid.feature.onboarding.di

import com.sci.recipeandroid.feature.onboarding.ui.viewmodel.PersonalizeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        PersonalizeViewModel(
            get()
        )
    }
}

