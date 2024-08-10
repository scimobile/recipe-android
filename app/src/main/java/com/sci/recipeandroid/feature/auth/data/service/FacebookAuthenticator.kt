package com.sci.recipeandroid.feature.auth.data.service

import android.content.Intent
import androidx.fragment.app.Fragment
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult

class FacebookAuthenticator {

    private val callbackManager: CallbackManager = CallbackManager.Factory.create()

    suspend fun onAuthentication(): Result<String> {
        val accessToken = AccessToken.getCurrentAccessToken()
        return try {
            if(accessToken != null && !accessToken.isExpired){
                Result.success(accessToken.token)
            } else {
                Result.failure(Exception("Facebook access token is invalid or expired"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    fun handleFacebookActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    fun facebookLogin(
        fragment: Fragment,
        onSuccess:() -> Unit,
        onCancel:() -> Unit,
        onError:() -> Unit
        ) {
        LoginManager.getInstance().logInWithReadPermissions(
            fragment,
            listOf("public_profile", "email")
        )
        LoginManager.getInstance().registerCallback(callbackManager, object :
            FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult) {
                onSuccess()
            }

            override fun onCancel() {
               onCancel()
            }

            override fun onError(error: FacebookException) {
               onError()
            }
        })
    }
}