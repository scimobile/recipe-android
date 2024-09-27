package com.sci.recipeandroid.feature.detail.ui.models

import com.sci.recipeandroid.feature.detail.domain.model.DetailFooterContainer
import com.sci.recipeandroid.feature.detail.domain.model.DetailScreenModel
import com.sci.recipeandroid.feature.detail.domain.model.IngredientsModel
import com.sci.recipeandroid.feature.detail.domain.model.NutritionPerServeModel

data class DetailSavedUiModels(
    val isBookMarked: Boolean = false,
    val detailFooterContainer: DetailFooterContainer,
)

data class DetailCenterUiModels(
    var serveAmt: Int,
    val selectedUnit: String,
    val isAddedToCart: Boolean,
    val nutritionPerServeModel: NutritionPerServeModel,
    var ingredients: List<IngredientUiModels>
) : DetailScreenModel()

data class IngredientUiModels(
    val ingredientModel: IngredientsModel,
    val selectedUnit: String
)