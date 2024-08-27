package com.sci.recipeandroid.feature.personalize.ui.model

import com.sci.recipeandroid.feature.personalize.domain.model.AllergiesIngredientModel

data class AllergiesIngredientUiModel(
    var isSelect: Boolean = false,
    var allergiesIngredientModel: AllergiesIngredientModel
)