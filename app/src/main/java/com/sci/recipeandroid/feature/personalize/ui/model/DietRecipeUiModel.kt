package com.sci.recipeandroid.feature.personalize.ui.model

import com.sci.recipeandroid.feature.personalize.domain.model.DietRecipeModel

data class DietRecipeUiModel(
    var isSelect: Boolean = false,
    var dietRecipeModel: DietRecipeModel
)