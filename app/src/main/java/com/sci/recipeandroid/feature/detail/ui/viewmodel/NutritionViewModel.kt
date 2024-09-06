package com.sci.recipeandroid.feature.detail.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sci.recipeandroid.feature.detail.domain.model.NutritionScreenData
import com.sci.recipeandroid.feature.detail.domain.repository.DetailRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NutritionViewModel(
    private val detailRepo: DetailRepo,
) : ViewModel() {
    private val _nutritionScnState = MutableLiveData<NutritionScreenState>()
    val nutritionScnState :LiveData<NutritionScreenState> = _nutritionScnState

    fun getNutritionData(id: Double) {
        viewModelScope.launch() {
            _nutritionScnState.value = NutritionScreenState.Loading
            detailRepo.getNutritionData(id)
                .fold(
                    onFailure = {},
                    onSuccess = {
                        _nutritionScnState.value = NutritionScreenState.Success(it)
                    }
                )
        }

    }

    sealed class NutritionScreenState {
        data object Loading : NutritionScreenState()
        data class Success(val data: List<NutritionScreenData>) : NutritionScreenState()
        data class Error(val message: String) : NutritionScreenState()
    }
}