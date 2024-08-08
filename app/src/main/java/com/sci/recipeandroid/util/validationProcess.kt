package com.sci.recipeandroid.util

import android.util.Patterns
import java.util.regex.Pattern

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
class ValidateEmail {

    fun execute(email: String): ValidationResult {
        if(email.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "The email can't be blank"
            )
        }

//        !email.endsWith("@gmail.com")

//        val emailPattern = Pattern.compile(
//            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
//                    "\\@" +
//                    "gmail.com"
//        )

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                successful = false,
                errorMessage = "That's not a valid email"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}
class ValidatePassword {

    fun execute(password: String): ValidationResult {

        if(password.length < 9) {
            return ValidationResult(
                successful = false,
                errorMessage = "9 characters minimum including at least 1 letter, 2 digits and 1 special characters"
            )
        }

        val digitPattern = "(.*\\d.*\\d)".toRegex()
        if (!digitPattern.containsMatchIn(password)) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password must contain at least 2 numbers"
            )
        }
        val specialCharacters = "(?=.*[!@#%^&()_+$\\-=\\[\\]{}|;':\",./<>?~`])".toRegex()
        if (!specialCharacters.containsMatchIn(password)) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password must contain at least 1 special character"
            )
        }
        val letterPattern = "(?=.*[a-zA-Z])".toRegex()
        if (!letterPattern.containsMatchIn(password)) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password must contain at least 1 letter"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}
class ValidateRepeatedPassword {

    fun execute(password: String, repeatedPassword: String): ValidationResult {
        if(password != repeatedPassword) {
            return ValidationResult(
                successful = false,
                errorMessage = "The passwords don't match"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}