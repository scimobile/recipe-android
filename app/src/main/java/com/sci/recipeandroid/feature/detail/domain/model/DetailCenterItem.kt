package com.sci.recipeandroid.feature.detail.domain.model

data class DetailCenterContainer(
    val nutritionPerServing: NutritionPerServing,
    var ingredients:List<Ingredients>
):DetailScreenData()

data class NutritionPerServing(
    val calories: String,
    val fat: String,
    val protein: String,
    val carbs: String
)
data class Ingredients(
    val name: String,
    var amount: Double,
    val amountUnit :String,
    val increaseRate:String,
    val image:String
)

