package com.sci.recipeandroid.feature.detail.data.datasource.remote

import com.sci.recipeandroid.feature.detail.data.model.NutritionResponse

class DetailRemoteDataSourceImpl():DetailRemoteDataSource {
    override fun getDetail(id: Double): Result<String> {
        return Result.success("")
    }

    override fun getNutritionPerServing(id: Double): Result<NutritionResponse> {
        TODO("Not yet implemented")
    }

    override fun getDirection(id: Double): Result<String> {
        TODO("Not yet implemented")
    }
}