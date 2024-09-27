package com.sci.recipeandroid.feature.cart.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sci.recipeandroid.feature.cart.data.repository.CartRecipeRepository
import com.sci.recipeandroid.feature.cart.ui.util.toRecipesUiModels
import com.sci.recipeandroid.feature.cart.ui.model.GroupedIngredientUiModel
import com.sci.recipeandroid.feature.cart.ui.model.RecipeUiModel
import com.sci.recipeandroid.feature.cart.ui.util.getGroupedIngredients
import com.sci.recipeandroid.util.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddToCartViewModel(
    private val cartRecipeRepository: CartRecipeRepository,
) : ViewModel() {

    private val _uiState = MutableLiveData<AddToCartUiState>()
    val uiState: LiveData<AddToCartUiState> = _uiState

    private val _uiEvent = SingleLiveEvent<AddToCartUiEvent>()
    val uiEvent: LiveData<AddToCartUiEvent> = _uiEvent

    private var recipes: List<RecipeUiModel> = emptyList()


    init {
        fetchRecipes()
    }

    fun fetchRecipes() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.postValue(AddToCartUiState.Loading)
            cartRecipeRepository.getAddedRecipeList().map {
                it.toRecipesUiModels()
            }.fold(
                onSuccess = {
                    recipes = it
                    updateUiState()
                },
                onFailure = {
                    _uiEvent.postValue(AddToCartUiEvent.Error("Failed to load data"))
                }
            )
        }
    }

    fun updateIngredientCheckedStatus(ingredientId: String, checked: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            cartRecipeRepository.updateIngredientCheckedStatus(ingredientId, checked).fold(
                onSuccess = {
                    recipes = recipes.map { recipe ->
                        recipe.copy(items = recipe.items.map { ingredient ->
                            if (ingredient.ingredientId == ingredientId) {
                                ingredient.copy(checked = checked)
                            } else ingredient
                        })
                    }
                    updateUiState()
                },
                onFailure = {
                    _uiEvent.postValue(AddToCartUiEvent.Error("Failed to update ingredient"))
                }
            )
        }
    }


    fun deleteRecipe(recipeId: String){
        viewModelScope.launch(Dispatchers.IO) {
            cartRecipeRepository.deleteRecipe(recipeId).fold(
                onSuccess = {
                    recipes = recipes.filterNot { it.recipeId == recipeId }
                    updateUiState()
                },
                onFailure = {
                    _uiEvent.postValue(AddToCartUiEvent.Error("Failed to delete recipe"))
                }
            )
        }
    }

    fun deleteAllRecipe(){
        viewModelScope.launch(Dispatchers.IO) {
            cartRecipeRepository.deleteAllRecipe().fold(
                onSuccess = {
                    recipes = emptyList()
                    updateUiState()
                },
                onFailure = {
                    _uiEvent.postValue(AddToCartUiEvent.Error("Failed to delete all list"))
                }
            )
        }
    }

    fun updateServingSize(recipeId: String, newServingSize: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            cartRecipeRepository.updateServingSize(recipeId, newServingSize).fold(
                onSuccess = {
                    recipes = recipes.map { recipe ->
                        if (recipe.recipeId == recipeId) {
                            val updatedIngredients = recipe.items.map { ingredient ->
                                ingredient.copy(
                                    amount = ingredient.amountPerPerson * newServingSize
                                )
                            }
                            recipe.copy(
                                servingCount = newServingSize,
                                items = updatedIngredients
                            )
                        } else recipe
                    }
                    updateUiState()
                },
                onFailure = {
                    _uiEvent.postValue(AddToCartUiEvent.Error("Failed to update serving count"))
                }
            )
        }
    }

    private fun updateUiState() {
        val groupedIngredients = recipes.getGroupedIngredients()
        _uiState.postValue(AddToCartUiState.Success(recipes, groupedIngredients))
    }


}

sealed class AddToCartUiState {
    object Loading : AddToCartUiState()

    data class Success(
        val recipes: List<RecipeUiModel>,
        val groupedIngredients: List<GroupedIngredientUiModel>
    ) : AddToCartUiState()
}


sealed class AddToCartUiEvent {
    data class Error(val message: String) : AddToCartUiEvent()
}
