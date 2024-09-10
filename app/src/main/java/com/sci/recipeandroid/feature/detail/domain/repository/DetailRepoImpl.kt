package com.sci.recipeandroid.feature.detail.domain.repository

import com.sci.recipeandroid.feature.detail.data.datasource.remote.DetailRemoteDataSource
import com.sci.recipeandroid.feature.detail.data.mapper.toDetailModel
import com.sci.recipeandroid.feature.detail.data.mapper.toDirectionModel
import com.sci.recipeandroid.feature.detail.data.mapper.toNutritionScreenData
import com.sci.recipeandroid.feature.detail.domain.model.DetailDataContainer
import com.sci.recipeandroid.feature.detail.domain.model.DirectionModel
import com.sci.recipeandroid.feature.detail.domain.model.NutritionFooterModel
import com.sci.recipeandroid.feature.detail.domain.model.NutritionHeaderModel
import com.sci.recipeandroid.feature.detail.domain.model.NutritionScreenData

class DetailRepoImpl(private val detailRemoteDataSource: DetailRemoteDataSource) : DetailRepo {
    override fun getDetail(id: Double): Result<DetailDataContainer> {
        return detailRemoteDataSource.getDetail(id).map {
            it.toDetailModel()
        }
    }

    override fun getNutritionData(id: Double): Result<List<NutritionScreenData>> {
        return detailRemoteDataSource.getNutritionPerServing(id).map { it ->
            val header = NutritionHeaderModel(
                calories = it.calories
            )
            val footer = NutritionFooterModel(
                notice = it.notice,
                disClaimer = it.disClaimer
            )
            buildList {
                add(header)
                addAll(it.nutritionTitle.toNutritionScreenData())
                add(footer)
            }
        }
    }

    override fun getDirection(id: Double): Result<List<DirectionModel>> {
        return detailRemoteDataSource.getDetail(id).map {
            it.toDirectionModel()
        }
    }
}
