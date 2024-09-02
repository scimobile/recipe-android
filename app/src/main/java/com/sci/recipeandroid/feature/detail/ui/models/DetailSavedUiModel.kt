package com.sci.recipeandroid.feature.detail.ui.models

import com.sci.recipeandroid.feature.detail.domain.model.DetailFooterContainer

data class DetailSavedUiModel(
    val detailIdList:Boolean=false,
    val detailFooterContainer: DetailFooterContainer,
)