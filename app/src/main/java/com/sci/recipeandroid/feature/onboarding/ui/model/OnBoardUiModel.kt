package com.sci.recipeandroid.feature.onboarding.ui.model

data class OnBoardUiModel(
    val title : String ?= "",

    val description : String ?= "",

    var imagePath : Int ?= 0,

    var isFirstPage: Boolean? = false
)