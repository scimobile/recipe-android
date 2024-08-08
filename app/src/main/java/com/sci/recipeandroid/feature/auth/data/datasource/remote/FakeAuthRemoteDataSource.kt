package com.sci.recipeandroid.feature.auth.data.datasource.remote

class FakeAuthRemoteDataSource: AuthRemoteDataSource {
    override suspend fun googleAuthentication(token:String): Result<String> {
        return Result.success("Success Ha Ha")
    }

    override suspend fun facebookAuthentication(token: String): Result<String> {
        TODO("Not yet implemented")
    }

}