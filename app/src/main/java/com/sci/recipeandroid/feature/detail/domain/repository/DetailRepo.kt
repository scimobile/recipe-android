package com.sci.recipeandroid.feature.detail.domain.repository

import com.sci.recipeandroid.feature.detail.domain.model.DetailDataContainer
import com.sci.recipeandroid.feature.detail.domain.model.DetailScreenData
import com.sci.recipeandroid.feature.detail.domain.model.NutritionScreenData

interface DetailRepo {
    fun getDetail(id :Double):Result<DetailDataContainer>

    fun getNutritionData(id: Double):Result<List<NutritionScreenData>>
}