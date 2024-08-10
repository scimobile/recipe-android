package com.sci.recipeandroid.feature.onboarding.di

import com.sci.recipeandroid.feature.onboarding.data.service.PersonalizeService
import com.sci.recipeandroid.feature.onboarding.network.KtorHelper
import org.koin.dsl.module

val networkModule = module {
    single {
        KtorHelper().getClient()
    }

    single {
        PersonalizeService(
            get()
        )
    }
}




