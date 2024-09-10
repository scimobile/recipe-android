package com.sci.recipeandroid.feature.detail.domain.model

data class DetailCenterContainer(
    val nutritionPerServeModel: NutritionPerServeModel,
    var ingredients:List<IngredientsModel>
):DetailScreenModel()

data class NutritionPerServeModel(
    val calories: String,
    val fat: String,
    val protein: String,
    val carbs: String
)
data class IngredientsModel(
    val name: String,
    var amount: Double,
    val amountUnit :String,
    val increaseRate:String,
    val image:String
)

