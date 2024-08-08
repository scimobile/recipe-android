package com.sci.recipeandroid.feature.auth.domain.repository

import com.sci.recipeandroid.feature.auth.data.datasource.remote.AuthRemoteDataSource

class AuthRepositoryImpl(
    private val authRemoteDataSource: AuthRemoteDataSource,
) : AuthRepository {
    override suspend fun googleAuthentication(token:String): Result<String> {
        return authRemoteDataSource.googleAuthentication(token)
    }

    override suspend fun facebookAuthentication(): Result<String> {
        TODO("Not yet implemented")
    }

}