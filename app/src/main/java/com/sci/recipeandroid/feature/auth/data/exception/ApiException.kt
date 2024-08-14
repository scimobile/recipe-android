package com.sci.recipeandroid.feature.auth.data.exception

class ApiException(message:String,val code:Int):Exception(message)

class GoogleAuthenticationException(message: String):Exception(message)