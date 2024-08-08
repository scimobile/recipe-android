package com.sci.recipeandroid.feature.auth.data.datasource.remote

class AuthRemoteDataSourceImpl(): AuthRemoteDataSource {
    override suspend fun googleAuthentication(token:String): Result<String> {
        return Result.success("")
    }

    override suspend fun facebookAuthentication(token: String): Result<String> {
        return Result.success("`")
    }
}