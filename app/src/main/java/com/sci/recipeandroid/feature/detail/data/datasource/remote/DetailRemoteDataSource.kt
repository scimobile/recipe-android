package com.sci.recipeandroid.feature.detail.data.datasource.remote

interface DetailRemoteDataSource {
    fun getDetail(id :Double):Result<String>
}