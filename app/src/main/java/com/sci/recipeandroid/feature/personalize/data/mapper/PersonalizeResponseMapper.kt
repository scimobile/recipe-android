package com.sci.recipeandroid.feature.personalize.data.mapper

import com.sci.recipeandroid.R
import com.sci.recipeandroid.feature.personalize.data.model.response.*
import com.sci.recipeandroid.feature.personalize.domain.model.*

fun mapIconResNameToId(iconResName: String): Int {
    return when (iconResName) {
        "eat_healthy_icon" -> R.drawable.eat_healthy_icon
        "budget_friendly_icon" -> R.drawable.budget_friendly_icon
        "plan_better_icon" -> R.drawable.plan_better_icon
        "learn_to_cook_icon" -> R.drawable.learn_to_cook_icon
        "quick_and_easy_icon" -> R.drawable.quick_and_easy_icon
        "none_image" -> R.drawable.none
        "vegan_image" -> R.drawable.vegan
        "vegetarian_image" -> R.drawable.vegetarian
        "pescatarian_image" -> R.drawable.pescatarian
        "paleo_image" -> R.drawable.paleo
        "low_crab_image" -> R.drawable.low_carb
        "keto_image" -> R.drawable.keto
        else -> R.drawable.eat_healthy_icon
    }
}

fun PersonalizeResponse.toGoalsDomainModel(): List<PersonalizeGoalsModel> {
    return data.personalizeGoals.map {
        PersonalizeGoalsModel(
            id = it.id,
            name = it.name.orEmpty(),
            iconUrl = it.iconUrl
        )
    }
}

fun PersonalizeResponse.toDietRecipeDomainModel(): List<DietRecipeModel> {
    return data.dietRecipe.map {
        DietRecipeModel(
            id = it.id,
            name = it.name.orEmpty(),
            imageUrl = it.imageUrl
        )
    }
}

fun PersonalizeResponse.toAllergiesIngredientModel(): List<AllergiesIngredientModel> {
    return data.allergiesIngredient.map {
        AllergiesIngredientModel(
            id = it.id,
            name = it.name.orEmpty()
        )
    }
}