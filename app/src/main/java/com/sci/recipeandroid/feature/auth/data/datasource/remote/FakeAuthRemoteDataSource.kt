package com.sci.recipeandroid.feature.auth.data.datasource.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FakeAuthRemoteDataSource: AuthRemoteDataSource {
    override suspend fun authenticateWithGoogle(token:String): Result<String> {
        return withContext(Dispatchers.IO){ Result.success("Success Ha Ha")}
    }

    override suspend fun authenticateWithFacebook(token: String): Result<String> {
        return withContext(Dispatchers.IO){ Result.success("Success")}
    }

    override suspend fun signUp(name: String, email: String, password: String): Result<String> {
        return withContext(Dispatchers.IO){ Result.success("signUpToken1234")}
    }

    override suspend fun logIn(email: String, password: String): Result<String> {
        return withContext(Dispatchers.IO){ Result.success("loginToken123")}
    }

}