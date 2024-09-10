package com.sci.recipeandroid.feature.detail.domain.model


abstract class DetailFooterItem

data class DetailFooterContainer(
    val detailFooterItems: List<DetailFooterItem>
) : DetailScreenModel()

data class CompleteMealContainer(
    var completeMealModelList: List<CompleteMealModel>
) : DetailFooterItem()

data class AlsoLikeContainer(
    var alsoLikeModelList: List<AlsoLikeModel>
) : DetailFooterItem()

data class RecipeOwnerContainer(
    val recipeOwnerName: String,
    val recipeOwnerImg: String,
    val ownerDescription: String
) : DetailFooterItem()

data class CompleteMealModel(
    val id: Double,
    val name: String,
    val image: String,
    val ratingPoint: Float,
    val ingredientAmt: Int,
    var isBookmarked: Boolean = false
)

data class AlsoLikeModel(
    val id: Double,
    val name: String,
    val image: String,
    val ingredientAmt: Int,
    var isBookmarked: Boolean = false
)