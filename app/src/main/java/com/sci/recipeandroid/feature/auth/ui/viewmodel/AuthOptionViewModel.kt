package com.sci.recipeandroid.feature.auth.ui.viewmodel

import android.content.Context
import android.content.Intent
import androidx.credentials.exceptions.GetCredentialCancellationException
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sci.recipeandroid.feature.auth.data.service.FacebookAuthenticator
import com.sci.recipeandroid.feature.auth.data.service.GoogleAuthenticator
import com.sci.recipeandroid.feature.auth.domain.repository.AuthRepository
import com.sci.recipeandroid.util.MyCustomContext
import com.sci.recipeandroid.util.SingleLiveEvent
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

class AuthOptionViewModel(
    private val authRepository: AuthRepository,
    private val googleAuthenticator: GoogleAuthenticator,
    private val facebookAuthenticator: FacebookAuthenticator,
) : ViewModel() {
    private val _uiEvent = SingleLiveEvent<String>()
    val uiEvent: LiveData<String> = _uiEvent

    fun loginSignUpWithGoogle(context: Context) {
        viewModelScope.launch(MyCustomContext(context)) {
            googleAuthenticator.generateGoogleToken()
                .fold(
                    onSuccess = {
                        authRepository.authenticateWithGoogle(it)
                            .fold(
                                onSuccess = { success: String ->
                                    _uiEvent.postValue(success)
                                },
                                onFailure = { error: Throwable ->
                                    _uiEvent.postValue(
                                        error.localizedMessage ?: "An Unknown Error Occurred"
                                    )
                                }
                            )
                    },
                    onFailure = { error:Throwable ->
                        if (error !is GetCredentialCancellationException) {
                            _uiEvent.postValue(
                                error.localizedMessage
                                    ?: "An Unknown Error Occurred"
                            )
                        }

                    }
                )
        }
    }

    fun loginWithFacebook(fragment: Fragment) {
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

    fun handleFacebookActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        facebookAuthenticator.handleFacebookActivityResult(
            resultCode = resultCode,
            requestCode = requestCode,
            data = data
        )

    }
    private fun facebookAuthentication(accessToken: String) {
        viewModelScope.launch {
            authRepository.authenticateWithFacebook(accessToken).fold(
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