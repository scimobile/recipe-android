package com.sci.recipeandroid.feature.auth.di

import android.content.Context
import androidx.room.Room
import com.sci.recipeandroid.feature.auth.data.datasource.db.AppDatabase
import com.sci.recipeandroid.feature.auth.data.datasource.db.UserDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

const val DB_NAME = "recipe_db"
val dbModule = module {
    single { provideRoomDatabase(context = androidContext()) }
    single { provideUserDao(appDatabase = get()) }

}

private fun provideRoomDatabase(context: Context): AppDatabase {
    val database: AppDatabase?
    //AppDatabase_impl
    database = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        DB_NAME
    ).build()
    return database
}

private fun provideUserDao(appDatabase: AppDatabase): UserDao = appDatabase.userDao()