package com.sci.recipeandroid.feature.auth.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sci.recipeandroid.feature.auth.domain.repository.AuthRepository
import com.sci.recipeandroid.util.SingleLiveEvent
import com.sci.recipeandroid.util.Validator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository,
    private val validator: Validator
) : ViewModel() {

    private val _loginFormState = MutableLiveData(LoginFormState())
    val loginFormState: LiveData<LoginFormState> = _loginFormState

    private val _loginScreenEvent = SingleLiveEvent<LoginScreenEvent>()
    val loginScreenEvent: LiveData<LoginScreenEvent> = _loginScreenEvent


    fun onEvent(loginFormEvent: LoginFormEvent) {
        when (loginFormEvent) {
            is LoginFormEvent.EmailChanged -> {
                _loginFormState.value = _loginFormState.value?.copy(email = loginFormEvent.email)
            }

            is LoginFormEvent.PasswordChange -> {
                _loginFormState.value =
                    _loginFormState.value?.copy(password = loginFormEvent.password)
            }
            is LoginFormEvent.Submit -> {
                _loginFormState.value?.let {
                    val emailResult = validator.emailValidator(it.email)
                    val passwordResult = it.password.isNotBlank()

                    if (emailResult.successful && passwordResult) {
                        logIn(it.email, it.password)
                    }else{
                        _loginFormState.value = _loginFormState.value?.copy(
                            emailError = emailResult.errorMessage,
                        )
                    }
                }
            }
        }
    }

    private fun logIn(email: String, password: String){
        viewModelScope.launch(Dispatchers.IO) {
            _loginScreenEvent.postValue(LoginScreenEvent.Loading)
            delay(3000)
            authRepository.logIn(email, password)
                .fold(
                    onFailure = {
                        _loginScreenEvent.postValue(
                            LoginScreenEvent.Error(it.localizedMessage)
                        )
                    },
                    onSuccess = {
                        _loginScreenEvent.postValue(LoginScreenEvent.Success)
                    }
                )
        }
    }

    fun clearEmailError() {
        _loginFormState.value = _loginFormState.value?.copy(emailError = null)
    }
}

sealed class LoginScreenEvent {
    data object Success : LoginScreenEvent()
    data class Error(val message: String?) : LoginScreenEvent()
    data object Loading : LoginScreenEvent()
}

sealed class LoginFormEvent {
    data class EmailChanged(val email: String) : LoginFormEvent()
    data class PasswordChange(val password: String) : LoginFormEvent()
    data object Submit : LoginFormEvent()
}

data class LoginFormState(
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
)