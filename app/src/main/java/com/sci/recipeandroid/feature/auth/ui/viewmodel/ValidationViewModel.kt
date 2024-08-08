package com.sci.recipeandroid.feature.auth.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sci.recipeandroid.util.ValidateEmail
import com.sci.recipeandroid.util.ValidatePassword
import com.sci.recipeandroid.util.ValidateRepeatedPassword
import kotlinx.coroutines.launch

class ValidationViewModel(
    private val validateEmail: ValidateEmail = ValidateEmail(),
    private val validatePassword: ValidatePassword = ValidatePassword(),
    private val validateRepeatedPassword: ValidateRepeatedPassword = ValidateRepeatedPassword(),
) : ViewModel(){
    
    private val _state = MutableLiveData(RegistrationFormState())
    val state: LiveData<RegistrationFormState> = _state
    private val _validationEvent = MutableLiveData<ValidationEvent>()
    val validationEvent: LiveData<ValidationEvent> = _validationEvent

    fun onEvent(event: RegistrationFormEvent) {
        when (event) {
            is RegistrationFormEvent.EmailChanged -> {
                _state.value = _state.value?.copy(email = event.email)
            }
            is RegistrationFormEvent.PasswordChanged -> {
                _state.value = _state.value?.copy(password = event.password)
            }
            is RegistrationFormEvent.RepeatedPasswordChanged -> {
                _state.value = _state.value?.copy(repeatedPassword = event.repeatedPassword)
            }
            is RegistrationFormEvent.AcceptTerms -> {
                _state.value = _state.value?.copy(acceptedTerms = event.isAccepted)
            }
            is RegistrationFormEvent.Submit -> {
                submitData()
            }

        }
    }
    fun clearEmailError() {
        _state.value = _state.value?.copy(emailError = null)
    }
    fun clearPasswordError() {
        _state.value = _state.value?.copy(passwordError = null)
    }
    fun clearRepeatedPasswordError() {
        _state.value = _state.value?.copy(repeatedPasswordError = null)
    }

    private fun submitData() {
        val currentState = _state.value ?: return
        val emailResult = validateEmail.execute(currentState.email)
        val passwordResult = validatePassword.execute(currentState.password)
        val repeatedPasswordResult = validateRepeatedPassword.execute(
            currentState.password, currentState.repeatedPassword
        )

        val hasError = listOf(
            emailResult,
            passwordResult,
            repeatedPasswordResult,
        ).any { !it.successful }

        if (hasError) {
            _state.value = currentState.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                repeatedPasswordError = repeatedPasswordResult.errorMessage,
            )
            return
        }
        viewModelScope.launch {
            _validationEvent.value = ValidationEvent.Success
        }
    }


}
sealed class ValidationEvent {
    object Success : ValidationEvent()
}
sealed class RegistrationFormEvent {
    data class EmailChanged(val email: String) : RegistrationFormEvent()
    data class PasswordChanged(val password: String) : RegistrationFormEvent()
    data class RepeatedPasswordChanged(
        val repeatedPassword: String
    ) : RegistrationFormEvent()

    data class AcceptTerms(val isAccepted: Boolean) : RegistrationFormEvent()

    object Submit: RegistrationFormEvent()
}
data class RegistrationFormState(
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val repeatedPassword: String = "",
    val repeatedPasswordError: String? = null,
    val acceptedTerms: Boolean = false,
)

