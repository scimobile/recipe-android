package com.sci.recipeandroid.feature.personalize.di

import com.sci.recipeandroid.feature.personalize.ui.viewmodel.PersonalizeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        PersonalizeViewModel(
            get()
        )
    }
}

