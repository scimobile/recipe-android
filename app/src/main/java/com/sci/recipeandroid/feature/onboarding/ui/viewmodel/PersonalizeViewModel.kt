package com.sci.recipeandroid.feature.onboarding.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sci.recipeandroid.feature.onboarding.data.repository.PersonalizeRepository
import com.sci.recipeandroid.feature.onboarding.domain.model.PersonalizeDataModel
import kotlinx.coroutines.launch

class PersonalizeViewModel(private val personalizeRepository: PersonalizeRepository) : ViewModel() {

    private val _liveData = MutableLiveData<PersonalizeUiState>()

    val liveData: LiveData<PersonalizeUiState> = _liveData



    init {
        _liveData.value = PersonalizeUiState.Loading
        viewModelScope.launch {
            try {
                val data = personalizeRepository.getPersonalizeData()
                _liveData.value = PersonalizeUiState.Success(data)
            } catch (e: Exception) {
                _liveData.value = PersonalizeUiState.Error(
                    icon = "",
                    message = e.message.toString()
                )
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        Log.d("viewmodel", "clear")
    }
}

sealed class PersonalizeUiState {
    data object Loading : PersonalizeUiState()
    data class Success(val personalizeData: PersonalizeDataModel) : PersonalizeUiState()

    data class Error(
        val icon: String,
        val message: String,
    ) : PersonalizeUiState()
}
