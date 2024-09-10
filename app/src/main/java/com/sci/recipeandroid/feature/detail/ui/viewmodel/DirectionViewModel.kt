package com.sci.recipeandroid.feature.detail.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sci.recipeandroid.feature.detail.domain.model.DirectionModel
import com.sci.recipeandroid.feature.detail.domain.repository.DetailRepo
import kotlinx.coroutines.launch

class DirectionViewModel(
    private val detailRepo: DetailRepo
): ViewModel() {
    private val _directionScnState = MutableLiveData<DirectionScreenState>()
    val directionScnState: LiveData<DirectionScreenState> = _directionScnState

    fun getDirection(id: Double){
        viewModelScope.launch {
            _directionScnState.value = DirectionScreenState.Loading
            detailRepo.getDirection(id).fold(
                onSuccess = {
                    _directionScnState.value = DirectionScreenState.Success(it)
                },
                onFailure = {}
            )
        }

    }

    sealed class DirectionScreenState{
        data object Loading: DirectionScreenState()
        data class Success(val data: List<DirectionModel>): DirectionScreenState()
        data class Error(val message: String): DirectionScreenState()
    }
}