package com.sci.recipeandroid.feature.auth.data.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_tb")
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Long = 1,
    @ColumnInfo(name = "user_name")
    val userName: String,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "phone_number")
    val phoneNumber: String,
    @ColumnInfo(name = "phone_number_three", defaultValue = "test")
    val phoneNumber3: String = "test"
)