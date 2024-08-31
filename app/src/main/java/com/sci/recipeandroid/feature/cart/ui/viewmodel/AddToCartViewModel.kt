package com.sci.recipeandroid.feature.cart.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sci.recipeandroid.feature.cart.domain.model.IngredientDetailModel
import com.sci.recipeandroid.feature.cart.domain.model.RecipeDetailModel
import com.sci.recipeandroid.feature.cart.domain.repository.CartRecipeRepository
import com.sci.recipeandroid.feature.cart.ui.mapper.toRecipesUiModels
import com.sci.recipeandroid.feature.cart.ui.model.IngredientUiModel
import com.sci.recipeandroid.feature.cart.ui.model.RecipeUiModel
import com.sci.recipeandroid.util.IngredientsSeparator
import kotlinx.coroutines.launch
import org.koin.core.definition.indexKey

class AddToCartViewModel(
    private val cartRecipeRepository: CartRecipeRepository,
    private val ingredientsSeparator: IngredientsSeparator
) : ViewModel() {

    private val _uiState = MutableLiveData<AddToCartUiState>()
    val uiState: LiveData<AddToCartUiState> = _uiState

    fun fetchRecipes() {
        _uiState.value = AddToCartUiState.Loading
        viewModelScope.launch {
            cartRecipeRepository.getAddedRecipeList().map {
                ingredientsSeparator.getGroupedIngredients(it.toRecipesUiModels())
            }.fold(
                onSuccess = { (recipes, groupedIngredients) ->
                    _uiState.postValue(AddToCartUiState.Success(recipes, groupedIngredients))
                },
                onFailure = {
                    _uiState.value = AddToCartUiState.Error("Failed to load data")
                }
            )
        }
    }

    fun updateIngredientCheckedStatus(ingredientId: String, checked: Boolean) {
        viewModelScope.launch {
            cartRecipeRepository.updateIngredientCheckedStatus(ingredientId, checked).map {
                ingredientsSeparator.getGroupedIngredients(it.toRecipesUiModels())
            }.fold(
                onSuccess = { (recipes, groupedIngredients) ->
                    _uiState.postValue(AddToCartUiState.Success(recipes, groupedIngredients))
                },
                onFailure = {
                    _uiState.value = AddToCartUiState.Error("Failed to load data")
                }
            )
        }
    }


}

sealed class AddToCartUiState {
    object Loading : AddToCartUiState()

    data class Success(
        val recipes: List<RecipeUiModel>,
        val groupedIngredients: Map<String, List<IngredientUiModel>>
    ) : AddToCartUiState()

    data class UpdateSuccess(
        val recipes: List<RecipeUiModel>,
        val groupedIngredients: Map<String, List<IngredientUiModel>>
    ): AddToCartUiState()

    data class Error(val message: String) : AddToCartUiState()
}
