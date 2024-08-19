package com.sci.recipeandroid.feature.personalize.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sci.recipeandroid.feature.personalize.domain.repository.PersonalizeRepository
import com.sci.recipeandroid.feature.personalize.ui.model.AllergiesIngredientUiModel
import com.sci.recipeandroid.feature.personalize.ui.model.DietRecipeUiModel
import com.sci.recipeandroid.util.SingleLiveEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PersonalizeViewModel(
    private val personalizeRepository: PersonalizeRepository
) : ViewModel() {

    private val _uiStateDietRecipe = MutableLiveData<DietRecipeUiState>()
    val uiStateDietRecipe: LiveData<DietRecipeUiState> = _uiStateDietRecipe
    var dietRecipeUiModel: List<DietRecipeUiModel> = emptyList()

    private val _uiStateAllergiesIngredient = MutableLiveData<AllergiesIngredientUiState>()
    val uiStateAllergiesIngredient: LiveData<AllergiesIngredientUiState> = _uiStateAllergiesIngredient
    var allergiesIngredientUiModel: List<AllergiesIngredientUiModel> = emptyList()

    private val _selectedItemDietRecipe = MutableLiveData<DietRecipeUiModel?>()
    val selectedItemDietRecipe: LiveData<DietRecipeUiModel?>
        get() = _selectedItemDietRecipe

    private val _selectedItemAllergiesIngredient = MutableLiveData<AllergiesIngredientUiModel?>()
    val selectedItemAllergiesIngredient: LiveData<AllergiesIngredientUiModel?>
        get() = _selectedItemAllergiesIngredient

    val uiEvent = SingleLiveEvent<PersonalizeUiEvent>()

    init {
        viewModelScope.launch {
            _uiStateDietRecipe.postValue(DietRecipeUiState.Loading)
            delay(1000)
            val resultDietRecipe = personalizeRepository.getDietRecipe()
            resultDietRecipe.onSuccess { items ->
                dietRecipeUiModel = items.map {
                    DietRecipeUiModel(dietRecipeModel = it)
                }
            }
            _uiStateDietRecipe.postValue(DietRecipeUiState.Success(dietRecipeUiModel))

            Log.d("PersonalizeViewModel", "Fetching allergies ingredients...")
            _uiStateAllergiesIngredient.postValue(AllergiesIngredientUiState.Loading)
            val resultAllergiesIngredient = personalizeRepository.getAllergiesIngredient()
            resultAllergiesIngredient.onSuccess { items ->
                Log.d("PersonalizeViewModel", "Allergies ingredients fetched successfully with ${items.size} items")
                allergiesIngredientUiModel = items.map {
                    AllergiesIngredientUiModel(allergiesIngredientModel = it)
                }
            }.onFailure {
                Log.e("PersonalizeViewModel", "Failed to fetch allergies ingredients", it)
            }
            _uiStateAllergiesIngredient.postValue(AllergiesIngredientUiState.Success(allergiesIngredientUiModel))
        }
    }

    fun selectItemDietRecipe(id: String?) {
        var selectedItem: DietRecipeUiModel? = null
        dietRecipeUiModel = dietRecipeUiModel.map { item ->
            if (item.dietRecipeModel.id == id) {
                selectedItem = item.copy(isSelect = true)
                selectedItem!!
            } else {
                item.copy(isSelect = false)
            }
        }
        _selectedItemDietRecipe.value = selectedItem
        _uiStateDietRecipe.postValue(
            DietRecipeUiState.UpdateDietRecipeList(dietRecipeUiModel)
        )
    }

    fun selectItemAllergiesIngredient(id: String?) {
        var selectedItem: AllergiesIngredientUiModel? = null
        allergiesIngredientUiModel = allergiesIngredientUiModel.map { item ->
            if (item.allergiesIngredientModel.id == id) {
                selectedItem = item.copy(isSelect = !item.isSelect)
                selectedItem!!
            } else {
                item
            }
        }
        _selectedItemAllergiesIngredient.value = selectedItem
        _uiStateAllergiesIngredient.postValue(
            AllergiesIngredientUiState.UpdateAllergiesIngredientList(allergiesIngredientUiModel)
        )
    }
}

sealed class DietRecipeUiState {
    data object Loading : DietRecipeUiState()
    data class Success(val dietRecipeList: List<DietRecipeUiModel>) : DietRecipeUiState()
    data class UpdateDietRecipeList(val items: List<DietRecipeUiModel>) : DietRecipeUiState()
}

sealed class AllergiesIngredientUiState {
    data object Loading : AllergiesIngredientUiState()
    data class Success(val allergiesIngredientList: List<AllergiesIngredientUiModel>) : AllergiesIngredientUiState()
    data class UpdateAllergiesIngredientList(val items: List<AllergiesIngredientUiModel>) : AllergiesIngredientUiState()
}

sealed class PersonalizeUiEvent {
    data class Error(
        val icon: String,
        val message: String,
    ) : PersonalizeUiEvent()
}