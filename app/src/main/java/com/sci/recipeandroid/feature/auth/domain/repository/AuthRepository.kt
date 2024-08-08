package com.sci.recipeandroid.feature.auth.domain.repository

import android.content.Context

interface AuthRepository {
    suspend fun googleAuthentication(token:String): Result<String>
    suspend fun facebookAuthentication(): Result<String>
}