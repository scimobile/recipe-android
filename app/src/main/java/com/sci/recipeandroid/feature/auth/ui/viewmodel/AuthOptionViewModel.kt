package com.sci.recipeandroid.feature.auth.ui.viewmodel

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sci.recipeandroid.feature.auth.data.service.FacebookAuthenticator
import com.sci.recipeandroid.feature.auth.data.service.GoogleAuthenticator
import com.sci.recipeandroid.feature.auth.domain.repository.AuthRepository
import com.sci.recipeandroid.util.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthOptionViewModel(
    private val authRepository: AuthRepository,
    private val googleAuthenticator: GoogleAuthenticator,
    private val facebookAuthenticator: FacebookAuthenticator,
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
        }
    }

    fun facebookLogin(fragment: Fragment){
        facebookAuthenticator.facebookLogin(fragment = fragment,
            onSuccess = { accessToken ->
                facebookAuthentication(accessToken)
            },
            onCancel = {
                _uiEvent.postValue("Facebook Authentication Cancelled")
            },
            onError = {
                _uiEvent.postValue(it.localizedMessage ?: "Something went wrong")
            })
    }

    fun handleFacebookActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        facebookAuthenticator.handleFacebookActivityResult(
            resultCode = resultCode,
            requestCode = requestCode,
            data = data
        )

    }
    private fun facebookAuthentication(accessToken: String) {
        viewModelScope.launch(Dispatchers.IO) {
            authRepository.facebookAuthentication(accessToken).fold(
                onSuccess = { token ->
                    _uiEvent.postValue(token)
                },
                onFailure = {
                    _uiEvent.postValue(it.localizedMessage ?: "Something went wrong")
                }
            )
        }
    }
}