package com.sci.recipeandroid.feature.onboarding.ui.model

data class OnBoardModel(
    var title : String ?= "",

    var description : String ?= "",

    var imagePath : Int ?= 0,

    var isFirstPage: Boolean? = false
)