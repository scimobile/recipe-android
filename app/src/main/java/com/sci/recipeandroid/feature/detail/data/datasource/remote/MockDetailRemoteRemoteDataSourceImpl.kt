package com.sci.recipeandroid.feature.detail.data.datasource.remote

class MockDetailRemoteRemoteDataSourceImpl():DetailRemoteDataSource {
    override fun getDetail(id: Double): Result<String> {
        return Result.success("")
    }

}