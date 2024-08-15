package com.sci.recipeandroid.feature.auth.data.datasource.local

import com.sci.recipeandroid.util.MMKVUtil

class AuthLocalDataSourceImpl:AuthLocalDataSource {
    override fun isUserLoggedIn(): Boolean= MMKVUtil.token.isNullOrEmpty()

    override fun saveToken(token: String) {
        MMKVUtil.token = token
    }

    override fun getToken(): String? = MMKVUtil.token
    override fun deleteToken() {
        MMKVUtil.token = null
    }
}