package com.sci.recipeandroid.feature.detail.domain.model

data class DetailHeaderContainerModel(
    val id:Double,
    val recipeImg:String,
    val recipeName:String,
    val recipeOwner:String,
    val ratingPoint:Float,
    val ratingCount:String,
    val isCommunityPick:Boolean,
    val description:String,
    val estimatedTime:String,
):DetailScreenModel()