package com.sci.recipeandroid.feature.auth.domain.repository

interface AuthRepository {
    suspend fun authenticateWithGoogle(token:String): Result<String>
    suspend fun authenticateWithFacebook(token: String): Result<String>
    suspend fun signUp(
        name:String,
        email:String,
        password:String
    ):Result<Unit>
    suspend fun logIn(email: String, password: String):Result<Unit>
}