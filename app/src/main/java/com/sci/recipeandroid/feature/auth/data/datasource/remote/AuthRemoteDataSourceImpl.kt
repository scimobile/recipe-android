package com.sci.recipeandroid.feature.auth.data.datasource.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRemoteDataSourceImpl(): AuthRemoteDataSource {
    override suspend fun authenticateWithGoogle(token:String): Result<String> {
        return withContext(Dispatchers.IO){
            Result.success("")
        }
    }

    override suspend fun authenticateWithFacebook(token: String): Result<String> {
        return withContext(Dispatchers.IO){
            Result.success("")
        }
    }

    override suspend fun signUp(name: String, email: String, password: String): Result<String> {
        return withContext(Dispatchers.IO){
            Result.success("signUpToken123")
        }
    }

    override suspend fun logIn(email: String, password: String): Result<String> {
        return withContext(Dispatchers.IO){
            Result.success("logInToken123")
        }
    }
}