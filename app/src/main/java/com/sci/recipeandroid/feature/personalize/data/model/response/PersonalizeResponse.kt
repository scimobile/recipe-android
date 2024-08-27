package com.sci.recipeandroid.feature.personalize.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class PersonalizeResponse(
    val code:Int?,
    val message:String?,
    val data: PersonalizeData
)


@Serializable
data class PersonalizeData(
    val dietRecipe:List<DietRecipe>,
    val allergiesIngredient:List<AllergiesIngredient>,
    val personalizeGoals:List<PersonalizeGoals>
)

@Serializable
data class DietRecipe(
    val id: String,
    val imageUrl:Int?,
    val name:String?
)

@Serializable
data class AllergiesIngredient (
    val id:String,
    val name:String?
)

@Serializable
data class PersonalizeGoals(
    val id: String,
    val iconUrl:Int?,
    val name:String?
)
