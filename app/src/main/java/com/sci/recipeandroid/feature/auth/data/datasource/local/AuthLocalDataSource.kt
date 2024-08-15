package com.sci.recipeandroid.feature.auth.data.datasource.local

interface AuthLocalDataSource {
    fun isUserLoggedIn():Boolean
    fun saveToken(token: String)
    fun getToken(): String?
    fun deleteToken()
}