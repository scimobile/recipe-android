package com.sci.recipeandroid.feature.detail.di

import com.sci.recipeandroid.feature.detail.data.datasource.remote.MockRecipeDetailRemoteDataSourceImpl
import com.sci.recipeandroid.feature.detail.data.datasource.remote.RecipeDetailRemoteDataSource
import com.sci.recipeandroid.feature.detail.domain.repository.RecipeDetailRepo
import com.sci.recipeandroid.feature.detail.domain.repository.RecipeDetailRepoImpl
import com.sci.recipeandroid.feature.detail.ui.viewmodel.DetailViewModel
import com.sci.recipeandroid.feature.detail.ui.viewmodel.DirectionViewModel
import com.sci.recipeandroid.feature.detail.ui.viewmodel.NutritionViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailDiModule = module {
    single {
        MockRecipeDetailRemoteDataSourceImpl() as RecipeDetailRemoteDataSource
    }
    single {
        RecipeDetailRepoImpl(
            recipeDetailRemoteDataSource = get()
        ) as RecipeDetailRepo
    }
    viewModel {
        DetailViewModel(
            recipeDetailRepo = get()
        )
    }
    viewModel {
        NutritionViewModel(
            recipeDetailRepo = get()
        )
    }
    viewModel {
        DirectionViewModel(
            recipeDetailRepo = get()
        )
    }
}