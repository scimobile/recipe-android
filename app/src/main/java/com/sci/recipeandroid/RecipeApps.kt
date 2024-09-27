package com.sci.recipeandroid

import android.app.Application
import com.sci.recipeandroid.feature.auth.di.authDiModule
import com.sci.recipeandroid.feature.detail.di.detailDiModule
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
                authDiModule,
                detailDiModule
            )
        }
    }
}