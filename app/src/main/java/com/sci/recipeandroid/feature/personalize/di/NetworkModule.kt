package com.sci.recipeandroid.feature.personalize.di

import com.sci.recipeandroid.feature.personalize.data.service.PersonalizeService
import com.sci.recipeandroid.feature.personalize.network.KtorHelper
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




