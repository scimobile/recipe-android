package com.sci.recipeandroid.feature.detail.domain.repository

import com.sci.recipeandroid.feature.detail.domain.model.DetailDataContainer
import com.sci.recipeandroid.feature.detail.domain.model.DirectionModel
import com.sci.recipeandroid.feature.detail.domain.model.NutritionScreenData

interface RecipeDetailRepo {
    fun getDetail(id :Double):Result<DetailDataContainer>

    fun getNutritionData(id: Double):Result<List<NutritionScreenData>>

    fun getDirection(id: Double):Result<List<DirectionModel>>
}