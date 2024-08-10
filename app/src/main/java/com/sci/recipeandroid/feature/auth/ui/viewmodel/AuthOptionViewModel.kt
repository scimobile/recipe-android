package com.sci.recipeandroid.feature.auth.ui.viewmodel

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
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
            onSuccess = {
                facebookAuthentication()
            },
            onCancel = {
                _uiEvent.postValue("User Cancelled")
            },
            onError = {
                _uiEvent.postValue("Error")
            })
    }

    fun handleFacebookActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        facebookAuthenticator.handleFacebookActivityResult(
            resultCode = resultCode,
            requestCode = requestCode,
            data = data
        )

    }
    private fun facebookAuthentication() {
        viewModelScope.launch(Dispatchers.IO) {
            facebookAuthenticator.onAuthentication().fold(
                onSuccess = {
                    authRepository.facebookAuthentication(it).fold(
                        onSuccess = { token ->
                            Log.d("AuthOptionViewModel","Token - $token" )
                            _uiEvent.postValue("Facebook login successful")
                        },
                        onFailure = {
                            _uiEvent.postValue(it.localizedMessage ?: "Something went wrong")
                        }
                    )
                },
                onFailure = {
                    _uiEvent.postValue(it.localizedMessage ?: "Something went wrong")
                }
            )
        }
    }
}