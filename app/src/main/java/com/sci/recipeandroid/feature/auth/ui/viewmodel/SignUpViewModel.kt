package com.sci.recipeandroid.feature.auth.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sci.recipeandroid.feature.auth.domain.repository.AuthRepository
import com.sci.recipeandroid.util.SingleLiveEvent
import com.sci.recipeandroid.util.ValidationResult
import com.sci.recipeandroid.util.Validator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val authRepository: AuthRepository,
    private val validator: Validator,
) : ViewModel() {
    private val _formState = MutableLiveData(RegistrationFormState())
    val formState: LiveData<RegistrationFormState> = _formState

    private val _signUpScreenEvent = SingleLiveEvent<SignUpScreenEvent>()
    val signUpScreenEvent: LiveData<SignUpScreenEvent> = _signUpScreenEvent

    fun onEvent(event: RegistrationFormEvent) {
        when (event) {
            is RegistrationFormEvent.NameChanged -> {
                _formState.value = _formState.value?.copy(displayName = event.displayName)
            }

            is RegistrationFormEvent.EmailChanged -> {
                _formState.value = _formState.value?.copy(email = event.email)
            }

            is RegistrationFormEvent.PasswordChanged -> {
                _formState.value = _formState.value?.copy(password = event.password)
            }

            is RegistrationFormEvent.RepeatedPasswordChanged -> {
                _formState.value = _formState.value?.copy(repeatedPassword = event.repeatedPassword)
            }

            is RegistrationFormEvent.AcceptTerms -> {
                _formState.value = _formState.value?.copy(acceptedTerms = event.isAccepted)
            }

            is RegistrationFormEvent.Submit -> {
                submitData()
            }

        }
    }

    fun clearEmailError() {
        _formState.value = _formState.value?.copy(emailError = null)
    }

    fun clearPasswordError() {
        _formState.value = _formState.value?.copy(passwordError = null)
    }

    fun clearRepeatedPasswordError() {
        _formState.value = _formState.value?.copy(repeatedPasswordError = null)
    }

    private fun submitData() {
        val currentState = _formState.value ?: return
        val nameResult = if (currentState.displayName.isBlank()) {
            ValidationResult(successful = false, errorMessage = "Your name can't be empty")
        } else {
            ValidationResult(successful = true)
        }
        val emailResult = validator.emailValidator(currentState.email)
        val passwordResult = validator.passwordValidator(currentState.password)
        val repeatedPasswordResult = validator.confirmPasswordValidator(
            currentState.password, currentState.repeatedPassword
        )

        val hasError = listOf(
            emailResult,
            passwordResult,
            repeatedPasswordResult,
            nameResult
        ).any { !it.successful }

        if (hasError) {
            _formState.value = currentState.copy(
                displayNameError = nameResult.errorMessage,
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                repeatedPasswordError = repeatedPasswordResult.errorMessage,
            )
            return
        } else {
            signUp(
                name = currentState.displayName,
                email = currentState.email,
                password = currentState.password
            )
        }
    }

    private fun signUp(
        name: String,
        email: String,
        password: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            _signUpScreenEvent.postValue(SignUpScreenEvent.Loading)
            delay(3000)
            authRepository.signUp(name, email, password)
                .fold(
                    onSuccess = {
                        _signUpScreenEvent.postValue(SignUpScreenEvent.Success)
                    },
                    onFailure = {
                        _signUpScreenEvent.postValue(SignUpScreenEvent.Error)
                    }
                )
        }
    }


}

sealed class SignUpScreenState {
    data object Idle : SignUpScreenState()

}

sealed class SignUpScreenEvent {
    data object Loading : SignUpScreenEvent()
    data object Success : SignUpScreenEvent()
    data object Error : SignUpScreenEvent()
}

sealed class RegistrationFormEvent {
    data class NameChanged(val displayName: String) : RegistrationFormEvent()
    data class EmailChanged(val email: String) : RegistrationFormEvent()
    data class PasswordChanged(val password: String) : RegistrationFormEvent()
    data class RepeatedPasswordChanged(
        val repeatedPassword: String
    ) : RegistrationFormEvent()

    data class AcceptTerms(val isAccepted: Boolean) : RegistrationFormEvent()

    data object Submit : RegistrationFormEvent()
}

data class RegistrationFormState(
    val displayName: String = "",
    val displayNameError: String? = null,
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val repeatedPassword: String = "",
    val repeatedPasswordError: String? = null,
    val acceptedTerms: Boolean = false,
)