package com.sci.recipeandroid.feature.detail.domain.repository

import com.sci.recipeandroid.feature.detail.domain.model.DetailDataContainer
import com.sci.recipeandroid.feature.detail.domain.model.DetailScreenData

interface DetailRepo {
    fun getDetail(id :Double):Result<DetailDataContainer>
}