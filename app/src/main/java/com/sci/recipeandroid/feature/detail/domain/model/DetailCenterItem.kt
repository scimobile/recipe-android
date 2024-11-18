package com.sci.recipeandroid.feature.detail.domain.model

data class DetailCenterContainer(
    val nutritionPerServeModel: NutritionPerServeModel,
    var ingredients: List<IngredientsModel>
) : DetailScreenModel()

data class NutritionPerServeModel(
    val calories: String,
    val fat: String,
    val protein: String,
    val carbs: String
)

data class IngredientsModel(
    val name: String,
    val increaseRate: Double,
    val image: String,
    var amountPerUsUnit: AmountPerUsUnit,
    var amountPerMetricUnit: AmountPerMetricUnit
)


data class AmountPerUsUnit(
    var usAmt: Double,
    val usUnit: String
)

data class AmountPerMetricUnit(
    var metricUnit: String,
    var metricAmt: Double
)



