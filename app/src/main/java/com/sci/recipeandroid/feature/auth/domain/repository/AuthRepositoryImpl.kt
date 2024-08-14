package com.sci.recipeandroid.feature.auth.domain.repository

import com.sci.recipeandroid.feature.auth.data.datasource.local.AuthLocalDataSource
import com.sci.recipeandroid.feature.auth.data.datasource.remote.AuthRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepositoryImpl(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val authLocalDataSource: AuthLocalDataSource,
) : AuthRepository {
    override suspend fun authenticateWithGoogle(token: String): Result<String> {
        return authRemoteDataSource.authenticateWithGoogle(token)
    }

    override suspend fun authenticateWithFacebook(token: String): Result<String> {
        return authRemoteDataSource.authenticateWithFacebook(token)
    }

    override suspend fun signUp(name: String, email: String, password: String): Result<Unit> {
        return authRemoteDataSource.signUp(name, email, password)
                .map { authLocalDataSource.saveToken(it) }

    }

    override suspend fun logIn(email: String, password: String): Result<Unit> {
        return authRemoteDataSource.logIn(email, password)
                .map { authLocalDataSource.saveToken(it) }
    }

}