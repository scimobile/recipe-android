package com.sci.recipeandroid

import android.app.Application
import com.sci.recipeandroid.feature.personalize.di.networkModule
import com.sci.recipeandroid.feature.personalize.di.remoteDataSourceModule
import com.sci.recipeandroid.feature.personalize.di.repositoryModule
import com.sci.recipeandroid.feature.personalize.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RecipeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                viewModelModule,
                networkModule,
                repositoryModule,
                remoteDataSourceModule
            )
            androidContext(this@RecipeApplication)
        }
    }
}