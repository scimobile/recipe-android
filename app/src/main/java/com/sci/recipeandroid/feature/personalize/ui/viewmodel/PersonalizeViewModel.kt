package com.sci.recipeandroid.feature.personalize.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sci.recipeandroid.feature.personalize.domain.repository.PersonalizeRepository
import com.sci.recipeandroid.feature.personalize.domain.model.PersonalizeDataModel
import com.sci.recipeandroid.util.SingleLiveEvent
import kotlinx.coroutines.launch

class PersonalizeViewModel(private val personalizeRepository: PersonalizeRepository) : ViewModel() {

    val uiEvent = SingleLiveEvent<PersonalizeUiEvent>()

    private val _uiState = MutableLiveData<PersonalizeUiState>()
    val uiState : LiveData<PersonalizeUiState> = _uiState

    private val _isDietRecipeItemSelected = MutableLiveData<Boolean>(false)
    private val isDietRecipeItemSelected: LiveData<Boolean> get() = _isDietRecipeItemSelected

    private val _isAllergiesIngredientItemSelected = MutableLiveData<Boolean>(false)
    private val isAllergiesIngredientItemSelected: LiveData<Boolean> get() = _isAllergiesIngredientItemSelected

    init {
        _uiState.value = PersonalizeUiState.Loading
        viewModelScope.launch {
            personalizeRepository.getPersonalizeData()
                .fold(
                    onSuccess = {
                        _uiState.value = PersonalizeUiState.Success(it)
                    },
                    onFailure = {
                        uiEvent.value = PersonalizeUiEvent.Error(
                            icon = "",
                            message = it.message.toString()
                        )
                    }
                )
        }
    }

    val isButtonEnabled: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        addSource(isDietRecipeItemSelected) { recyclerViewSelected ->
            value = recyclerViewSelected && _isAllergiesIngredientItemSelected.value == true
        }
        addSource(isAllergiesIngredientItemSelected) { tagViewSelected ->
            value = tagViewSelected && _isDietRecipeItemSelected.value == true
        }
    }

    fun onDietRecipeItemSelected(selected: Boolean) {
        _isDietRecipeItemSelected.value = selected
    }

    fun onAllergiesIngredientItemSelected(selected: Boolean) {
        _isAllergiesIngredientItemSelected.value = selected
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("viewmodel", "clear")
    }
}

sealed class PersonalizeUiState {
    data object Loading : PersonalizeUiState()
    data class Success(val personalizeData: PersonalizeDataModel) : PersonalizeUiState()
}

sealed class PersonalizeUiEvent {
    data class Error(
        val icon: String,
        val message: String,
    ) : PersonalizeUiEvent()
}