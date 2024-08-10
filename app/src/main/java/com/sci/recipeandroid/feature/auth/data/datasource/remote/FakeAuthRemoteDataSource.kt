package com.sci.recipeandroid.feature.auth.data.datasource.remote

class FakeAuthRemoteDataSource: AuthRemoteDataSource {
    override suspend fun googleAuthentication(token:String): Result<String> {
        return Result.success("Success Ha Ha")
    }

    override suspend fun facebookAuthentication(token: String): Result<String> {
        return Result.success(token)
    }

    override suspend fun signUp(name: String, email: String, password: String): Result<String> {
        return Result.success("signUpToken1234")
    }

    override suspend fun logIn(email: String, password: String): Result<String> {
        return Result.success("loginToken123")
    }

}