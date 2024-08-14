package com.sci.recipeandroid.feature.auth.data.service

import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialCancellationException
import androidx.credentials.exceptions.GetCredentialException
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.sci.recipeandroid.feature.auth.data.exception.GoogleAuthenticationException
import com.sci.recipeandroid.util.MyCustomContext
import kotlin.coroutines.coroutineContext

class GoogleAuthenticator() {
    //TODO has to add server client id into gradle and handle exception
    suspend fun generateGoogleToken(): Result<String> {
        val contexts = coroutineContext[MyCustomContext]?.context!!
        val credentialManager = CredentialManager.create(contexts)
        val googleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId("512517043540-n2607og1tv5an2oqj179cifj2pqhtg02.apps.googleusercontent.com")
            .build()

        val credentialRequest = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()
        return try {
            val result = credentialManager.getCredential(contexts, credentialRequest)
            when (val credential = result.credential) {
                is CustomCredential -> {
                    if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                        try {
                            // Use googleIdTokenCredential and extract the ID to validate and
                            // authenticate to the server.
                            val googleIdTokenCredential = GoogleIdTokenCredential
                                .createFrom(credential.data)
                            Result.success(googleIdTokenCredential.idToken)
                        } catch (e: GoogleIdTokenParsingException) {
                            Log.e("Error", "Received an invalid google id token response", e)
                            Result.failure(GoogleAuthenticationException("An Unknown Error Occurs"))
                        }
                    } else {
                        Result.failure(GoogleAuthenticationException("Unexpected error Occurs"))
                    }
                }

                else -> Result.failure(GoogleAuthenticationException("Unexpected error Occurs"))
            }
        } catch (e: GetCredentialCancellationException) {
            Result.failure(e)
        } catch (e: GetCredentialException) {
            val message = e.localizedMessage?.toString() ?: "An Unknown Error Occurred"
            Log.e("Credential Error", message)
            Result.failure(GoogleAuthenticationException("An Unknown Error Occurs"))
        }
    }
}