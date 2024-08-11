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

    fun handleFacebookActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    fun facebookLogin(
        fragment: Fragment,
        onSuccess:(String) -> Unit,
        onCancel:() -> Unit,
        onError:(FacebookException) -> Unit
        ) {
        LoginManager.getInstance().logInWithReadPermissions(
            fragment,
            listOf("public_profile", "email")
        )
        LoginManager.getInstance().registerCallback(callbackManager, object :
            FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult) {
                onSuccess(result.accessToken.token)
            }

            override fun onCancel() {
               onCancel()
            }

            override fun onError(error: FacebookException) {
               onError(error)
            }
        })
    }
}