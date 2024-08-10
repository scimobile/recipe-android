package com.sci.recipeandroid.feature.auth.data.service

import android.content.Context
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException

class GoogleAuthenticator {
    //TODO has to add server client id into gradle and handle exception
    suspend fun onAuthenticate(context: Context): Result<String> {
        val credentialManager = CredentialManager.create(context)
        val googleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId("512517043540-n2607og1tv5an2oqj179cifj2pqhtg02.apps.googleusercontent.com")
            .build()

        val credentialRequest = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()
        return try {
            val result = credentialManager.getCredential(context, credentialRequest)
            when (val credential = result.credential) {
                is CustomCredential -> {
                    if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                        try {
                            // Use googleIdTokenCredential and extract the ID to validate and
                            // authenticate to the server.
                            val googleIdTokenCredential = GoogleIdTokenCredential
                                .createFrom(credential.data)
                            Log.d("Success", googleIdTokenCredential.idToken)
                            Result.success(googleIdTokenCredential.idToken)
                        } catch (e: GoogleIdTokenParsingException) {
                            Log.e("Error", "Received an invalid google id token response", e)
                            Result.failure(Exception(e.localizedMessage))
                        }
                    } else {
                        Result.failure(Exception("Unexpected type of custom credential"))
                    }
                }

                else -> Result.failure(Exception("Unexpected type of credential"))
            }
        } catch (e: GetCredentialException) {
            Log.e("Error", e.localizedMessage?.toString() ?: "An Unknown Error Occurred")
            Result.failure(Exception(e.localizedMessage))
        }
    }
}