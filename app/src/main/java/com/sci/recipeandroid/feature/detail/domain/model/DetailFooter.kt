package com.sci.recipeandroid.feature.detail.domain.model


abstract class DetailFooterItem

data class DetailFooterContainer(
    val detailFooterItems:List<DetailFooterItem>
):DetailScreenData()

data class CompleteMealContainer(
    val completeMealList:List<CompleteMeal>
):DetailFooterItem()

data class AlsoLikeContainer(
    val alsoLikeList:List<AlsoLike>
):DetailFooterItem()

data class RecipeOwnerContainer(
    val recipeOwnerName:String,
    val recipeOwnerImg:String,
    val ownerDescription:String
):DetailFooterItem()

data class CompleteMeal(
    val name:String,
    val image:String,
    val ratingPoint:Float,
    val ingredientAmt:Int
)

data class AlsoLike(
    val name:String,
    val image:String,
    val ingredientAmt:Int
)