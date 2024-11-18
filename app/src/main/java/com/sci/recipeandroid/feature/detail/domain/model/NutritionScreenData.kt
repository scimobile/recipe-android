package com.sci.recipeandroid.feature.detail.domain.model

abstract class NutritionScreenData

data class NutritionHeaderModel(
    val calories: String
): NutritionScreenData()
data class NutritionModel(
    val id: Double,
    val typeName: String,
    val dailyValue: String,
    val amount: String,
) : NutritionScreenData()

data class SubTypeNutritionModel(
    val id: Double,
    val subName: String,
    val subAmount: String,
    val subDailyValue: String
) : NutritionScreenData()

data class NutritionFooterModel(
    val notice:String,
    val disClaimer :String
):NutritionScreenData()