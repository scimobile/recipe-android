package com.sci.recipeandroid.feature.detail.data.model


data class NutritionResponse(
    val calories :String,
    val nutritionTitle: List<Nutrition>,
    val notice:String,
    val disClaimer:String
)
data class Nutrition(
    val id: Double,
    val typeName: String,
    val dailyValue: String,
    val amount:String,
    val subType: List<NutritionSubType>
)

data class NutritionSubType(
    val id: Double,
    val typeName: String,
    val subAmount: String,
    val dailyValue: String
)