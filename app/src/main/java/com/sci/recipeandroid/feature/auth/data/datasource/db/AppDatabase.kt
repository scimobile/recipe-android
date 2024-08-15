package com.sci.recipeandroid.feature.auth.data.datasource.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sci.recipeandroid.feature.auth.data.model.entities.UserEntity

@Database(
    entities = [
        UserEntity::class,
    ],
    version = 1,
    exportSchema = true,
)
abstract class AppDatabase:RoomDatabase() {
    abstract fun userDao(): UserDao
}