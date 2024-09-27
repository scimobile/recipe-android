package com.sci.recipeandroid.feature.detail.di

import com.sci.recipeandroid.feature.detail.domain.repository.DetailRepo
import com.sci.recipeandroid.feature.detail.domain.repository.DetailRepoImpl
import com.sci.recipeandroid.feature.detail.ui.viewmodel.DetailViewModel
import com.sci.recipeandroid.feature.detail.ui.viewmodel.DirectionViewModel
import com.sci.recipeandroid.feature.detail.ui.viewmodel.NutritionViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailDiModule = module {
    single {
        DetailRepoImpl(
            detailRemoteDataSource = get()
        ) as DetailRepo
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