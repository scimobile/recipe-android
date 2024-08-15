package com.sci.recipeandroid.feature.personalize.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class PersonalizeGoalsModel(
    val id: Int,
    val name: String?,
    val iconUrl: Int?
)