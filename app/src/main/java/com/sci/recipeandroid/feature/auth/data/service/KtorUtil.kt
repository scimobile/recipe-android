package com.sci.recipeandroid.feature.auth.data.service

import android.content.Context
import com.sci.recipeandroid.feature.auth.data.datasource.local.AuthLocalDataSource
import com.sci.recipeandroid.feature.auth.data.datasource.local.AuthLocalDataSourceImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object KtorUtil {
    fun createKtor(
        appContext: Context,
        authLocalDataSource: AuthLocalDataSource
    ): HttpClient {
        return HttpClient(OkHttp){
            install(ContentNegotiation){
                json(
                    Json { ignoreUnknownKeys = true}
                )
            }
            install(ResponseObserver){
                onResponse {  }
            }
        }.apply {}
    }
}