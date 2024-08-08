package com.sci.recipeandroid.feature.auth.di

import com.sci.recipeandroid.feature.auth.data.service.FacebookAuthenticator
import com.sci.recipeandroid.feature.auth.data.service.GoogleAuthenticator
import org.koin.dsl.module

val socialAuthModule = module {
    factory {
        GoogleAuthenticator()
    }
    factory {
        FacebookAuthenticator()
    }
}