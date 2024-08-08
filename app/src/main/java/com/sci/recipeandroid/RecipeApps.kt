package com.sci.recipeandroid

import android.app.Application
import com.sci.recipeandroid.feature.auth.di.remoteDataSourceModule
import com.sci.recipeandroid.feature.auth.di.repositoryModule
import com.sci.recipeandroid.feature.auth.di.socialAuthModule
import com.sci.recipeandroid.feature.auth.di.viewModelModule
import com.tencent.mmkv.MMKV
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RecipeApps: Application() {
    override fun onCreate() {
        super.onCreate()
        MMKV.initialize(this)
        startKoin {
            androidContext(this@RecipeApps)
            modules(
                socialAuthModule,
                remoteDataSourceModule,
                repositoryModule,
                viewModelModule
            )
        }
    }
}