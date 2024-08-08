package com.sci.recipeandroid.feature.auth.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sci.recipeandroid.feature.auth.data.service.GoogleAuthenticator
import com.sci.recipeandroid.feature.auth.domain.repository.AuthRepository
import com.sci.recipeandroid.util.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthOptionViewModel(
    private val authRepository: AuthRepository,
    private val googleAuthenticator: GoogleAuthenticator
) : ViewModel() {
    private val _uiEvent = SingleLiveEvent<String>()
    val uiEvent: LiveData<String> = _uiEvent

    fun googleAuthentication(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            googleAuthenticator.onAuthenticate(context)
                .fold(
                    onSuccess = {
                        authRepository.googleAuthentication(it)
                            .fold(
                                onSuccess = { success: String ->
                                    _uiEvent.postValue(success)
                                },
                                onFailure = { backError: Throwable ->
                                    _uiEvent.postValue(
                                        backError.localizedMessage ?: "An Unknown Error Occurred"
                                    )
                                }
                            )
                    },
                    onFailure = {
                        _uiEvent.postValue(
                            it.localizedMessage
                                ?: "An Unknown Error Occurred"
                        )
                    }
                )
//            authRepository.googleAuthentication(context = context)
//                .fold(
//                    onSuccess = {
//                        _uiEvent.postValue("")
//                    },
//                    onFailure = {
//                        _uiEvent.postValue(it.localizedMessage ?: "An Unknown Error Occurred")
//                    }
//                )
        }
    }
}