package com.sci.recipeandroid.feature.auth.data.datasource.remote

import android.content.Context

interface AuthRemoteDataSource {
    suspend fun authenticateWithGoogle(token:String):Result<String>
    suspend fun authenticateWithFacebook(token:String):Result<String>
    suspend fun signUp(name:String,email:String,password:String):Result<String>
    suspend fun logIn(email:String,password:String):Result<String>
}