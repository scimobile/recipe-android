package com.sci.recipeandroid.feature.personalize.data.mapper

import com.sci.recipeandroid.R
import com.sci.recipeandroid.feature.personalize.data.model.response.*
import com.sci.recipeandroid.feature.personalize.domain.model.*

// Map PersonalizeResponse data to PersonalizeDataModel
fun PersonalizeResponse.toDomainModel(): PersonalizeDataModel {
    return PersonalizeDataModel(
        dietRecipe = this.data.dietRecipe.map { it.toDomainModel() },
        allergiesIngredient = this.data.allergiesIngredient.map { it.toDomainModel() },
        personalizeGoals = this.data.personalizeGoals.map { it.toDomainModel() }
    )
}

// Map DietRecipe data model to domain model
fun DietRecipe.toDomainModel(): DietRecipeModel {
    return DietRecipeModel(
        id = this.id,
        name = this.name.orEmpty(),
        imageUrl = this.imageUrl.orEmpty()
    )
}

// Map AllergiesIngredient data model to domain model
fun AllergiesIngredient.toDomainModel(): AllergiesIngredientModel {
    return AllergiesIngredientModel(
        id = this.id,
        name = this.name.orEmpty()
    )
}

// Map PersonalizeGoals data model to domain model
fun PersonalizeGoals.toDomainModel(): PersonalizeGoalsModel {
    return PersonalizeGoalsModel(
        id = this.id,
        name = this.name.orEmpty(),
        iconUrl = mapIconResNameToId(this.iconUrl.orEmpty())
    )
}

// Function to map the icon URL (or resource name) to a drawable resource ID
fun mapIconResNameToId(iconResName: String): Int {
    return when (iconResName) {
        "eat_healthy_icon" -> R.drawable.eat_healthy_icon
        "budget_friendly_icon" -> R.drawable.budget_friendly_icon
        "plan_better_icon" -> R.drawable.plan_better_icon
        "learn_to_cook_icon" -> R.drawable.learn_to_cook_icon
        "quick_and_easy_icon" -> R.drawable.quick_and_easy_icon
        else -> R.drawable.eat_healthy_icon // Use a default icon as a fallback
    }
}