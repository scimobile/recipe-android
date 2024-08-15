package com.sci.recipeandroid.feature.auth.data.datasource.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sci.recipeandroid.feature.auth.data.model.entities.UserEntity


@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(user: UserEntity): Long

    @Query("SELECT * FROM user_tb LIMIT 1")
    fun getUser(): LiveData<UserEntity>

    @Query("SELECT * FROM user_tb LIMIT 1")
    suspend fun getUserOnce(): UserEntity?

    @Delete
    fun deleteUser(user: UserEntity)

}