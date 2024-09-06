package com.sci.recipeandroid.feature.detail.data.datasource.remote

import com.sci.recipeandroid.feature.detail.data.model.NutritionResponse


interface DetailRemoteDataSource {
    fun getDetail(id: Double): Result<String>
    fun getNutritionPerServing(id: Double): Result<NutritionResponse>
    fun getDirection(id: Double): Result<String>
}