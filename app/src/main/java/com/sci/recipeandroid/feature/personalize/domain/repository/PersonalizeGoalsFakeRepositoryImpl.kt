package com.sci.recipeandroid.feature.personalize.domain.repository

import com.sci.recipeandroid.feature.personalize.data.mapper.mapIconResNameToId
import com.sci.recipeandroid.feature.personalize.domain.model.PersonalizeGoalsModel

class PersonalizeGoalsFakeRepositoryImpl: PersonalizeGoalsFakeRepository {

    data class RawGoalData(val id: Int, val name: String, val iconResName: String)

    override fun getPersonalizeGoals(): List<PersonalizeGoalsModel> {

        val rawData = listOf(
            RawGoalData(1, "Eat Healthy", "eat_healthy_icon"),
            RawGoalData(2, "Budget Friendly", "budget_friendly_icon"),
            RawGoalData(3, "Plan Better", "plan_better_icon"),
            RawGoalData(4, "Learn to Cook", "learn_to_cook_icon"),
            RawGoalData(5, "Quick & Easy", "quick_and_easy_icon")
        )

        return rawData.map { (id, name, iconResName) ->
            PersonalizeGoalsModel(
                id = id,
                name = name,
                iconUrl = mapIconResNameToId(iconResName)
            )
        }
    }

    override fun sendSelectedGoal(goal: PersonalizeGoalsModel, callback: (Result<Unit>) -> Unit) {
        try {
            callback(Result.success(Unit))
        } catch (e: Exception) {
            callback(Result.failure(e))
        }
    }
}
