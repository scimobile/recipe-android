package com.sci.recipeandroid.feature.detail.domain.repository

import com.sci.recipeandroid.feature.detail.data.datasource.remote.DetailRemoteDataSource
import com.sci.recipeandroid.feature.detail.data.mapper.toDetailData
import com.sci.recipeandroid.feature.detail.domain.model.DetailDataContainer

class DetailRepoImpl(private val detailRemoteDataSource: DetailRemoteDataSource):DetailRepo {
    override fun getDetail(id: Double): Result<DetailDataContainer> {
        return detailRemoteDataSource.getDetail(id).map {
            it.toDetailData()
        }
    }
}