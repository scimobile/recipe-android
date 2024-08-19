package com.sci.recipeandroid.feature.personalize.ui.model

import com.sci.recipeandroid.feature.personalize.domain.model.PersonalizeGoalsModel

data class PersonalizeGoalsUiModel(
    var isSelect: Boolean = false,
    val personalizeGoalsModel: PersonalizeGoalsModel
)

