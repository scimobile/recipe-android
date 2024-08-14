package com.sci.recipeandroid.feature.personalize.domain.repository

import com.sci.recipeandroid.feature.personalize.data.mapper.mapIconResNameToId
import com.sci.recipeandroid.feature.personalize.domain.model.PersonalizeGoalsModel
import com.sci.recipeandroid.feature.personalize.domain.repository.PersonalizeGoalsFakeRepositoryImpl.RawGoalData

interface PersonalizeGoalsFakeRepository {

    fun getPersonalizeGoals(): List<PersonalizeGoalsModel>
    fun sendSelectedGoal(goal: PersonalizeGoalsModel, callback: (Result<Unit>) -> Unit)
}