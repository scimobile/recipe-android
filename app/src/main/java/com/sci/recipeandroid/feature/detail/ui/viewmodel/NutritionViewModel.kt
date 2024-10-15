package com.sci.recipeandroid.feature.detail.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sci.recipeandroid.feature.detail.domain.model.NutritionScreenData
import com.sci.recipeandroid.feature.detail.domain.repository.RecipeDetailRepo
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NutritionViewModel(
    private val recipeDetailRepo: RecipeDetailRepo,
) : ViewModel() {
    private val _nutritionScnState = MutableLiveData<NutritionScreenState>()
    val nutritionScnState :LiveData<NutritionScreenState> = _nutritionScnState

    fun getNutritionData(id: Double) {
        viewModelScope.launch() {
            _nutritionScnState.value = NutritionScreenState.Loading
            delay(3000)
            recipeDetailRepo.getNutritionData(id)
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