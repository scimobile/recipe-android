package com.sci.recipeandroid.feature.personalize.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sci.recipeandroid.feature.personalize.domain.repository.PersonalizeGoalsFakeRepositoryImpl
import com.sci.recipeandroid.feature.personalize.domain.model.PersonalizeGoalsModel

class PersonalizeGoalsViewModel : ViewModel() {

    private val repository = PersonalizeGoalsFakeRepositoryImpl()

    private val _uiState = MutableLiveData<PersonalizeGoalsUiState>()
    val uiState: LiveData<PersonalizeGoalsUiState> = _uiState

    private val _selectedGoal = MutableLiveData<PersonalizeGoalsModel?>()
    val selectedGoal: LiveData<PersonalizeGoalsModel?> = _selectedGoal

    init {
        loadGoals()
    }

    private fun loadGoals() {
        _uiState.value = PersonalizeGoalsUiState.Loading
        val goals = repository.getPersonalizeGoals()
        _uiState.value = PersonalizeGoalsUiState.Success(PersonalizeGoalsUiData(goals = goals))
    }

    fun selectGoal(goal: PersonalizeGoalsModel) {
        _selectedGoal.value = goal
    }

    fun sendSelectedGoal() {
        val goal = _selectedGoal.value
        if (goal != null) {
            _uiState.value = PersonalizeGoalsUiState.Loading

            repository.sendSelectedGoal(goal) { result: Result<Unit> ->
                result.onSuccess {
                    _uiState.postValue(PersonalizeGoalsUiState.SuccessSending)
                }.onFailure { e ->
                    _uiState.postValue(PersonalizeGoalsUiState.Error(e.message ?: "Unknown error"))
                }
            }
        } else {
            _uiState.value = PersonalizeGoalsUiState.Error("No goal selected")
        }
    }
}

sealed class PersonalizeGoalsUiState {
    object Loading : PersonalizeGoalsUiState()
    data class Success(val personalizeData: PersonalizeGoalsUiData) : PersonalizeGoalsUiState()
    data class Error(val message: String) : PersonalizeGoalsUiState()
    object SuccessSending : PersonalizeGoalsUiState()
}

data class PersonalizeGoalsUiData(val goals: List<PersonalizeGoalsModel>)
