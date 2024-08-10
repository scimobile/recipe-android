package com.sci.recipeandroid.feature.onboarding.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PersonalizeResponse(
    val code:Int?,
    val message:String?,
    val data:PersonalizeData
)


@Serializable
data class PersonalizeData(
    val dietRecipe:List<DietRecipe>,
    val allergiesIngredient:List<AllergiesIngredient>
)

@Serializable
data class DietRecipe (
    val id:String,
    val imageUrl:String?,
    val name:String?
)

@Serializable
data class AllergiesIngredient (
    val id:String,
    val name:String?
)
