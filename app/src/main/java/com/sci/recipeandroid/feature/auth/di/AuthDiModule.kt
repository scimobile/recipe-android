package com.sci.recipeandroid.feature.auth.di

import com.sci.recipeandroid.feature.auth.data.datasource.local.AuthLocalDataSource
import com.sci.recipeandroid.feature.auth.data.datasource.local.AuthLocalDataSourceImpl
import com.sci.recipeandroid.feature.auth.data.datasource.remote.AuthRemoteDataSource
import com.sci.recipeandroid.feature.auth.data.datasource.remote.FakeAuthRemoteDataSource
import com.sci.recipeandroid.feature.auth.data.service.FacebookAuthenticator
import com.sci.recipeandroid.feature.auth.data.service.GoogleAuthenticator
import com.sci.recipeandroid.feature.auth.domain.repository.AuthRepository
import com.sci.recipeandroid.feature.auth.domain.repository.AuthRepositoryImpl
import com.sci.recipeandroid.feature.auth.ui.viewmodel.AuthOptionViewModel
import com.sci.recipeandroid.feature.auth.ui.viewmodel.LoginViewModel
import com.sci.recipeandroid.feature.auth.ui.viewmodel.SignUpViewModel
import com.sci.recipeandroid.feature.detail.data.datasource.remote.DetailRemoteDataSource
import com.sci.recipeandroid.feature.detail.data.datasource.remote.MockDetailRemoteDataSourceImpl
import com.sci.recipeandroid.util.Validator
import com.sci.recipeandroid.util.ValidatorImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authDiModule = module {
    //region data source
    single {
//        AuthRemoteDataSourceImpl(
//            googleAuthenticator = get(),
//            facebookAuthenticator = get()
//        ) as AuthRemoteDataSource
        FakeAuthRemoteDataSource() as AuthRemoteDataSource
    }
    single {
        MockDetailRemoteDataSourceImpl() as DetailRemoteDataSource
    }

    single {
        AuthLocalDataSourceImpl() as AuthLocalDataSource
    }
    //endregion

    //region repo
    single {
        AuthRepositoryImpl(
            authRemoteDataSource = get(),
            authLocalDataSource = get()
        ) as AuthRepository
    }
    //endregion

    //region viewmodel
    viewModel {
        AuthOptionViewModel(
            authRepository = get(),
            googleAuthenticator = get(),
            facebookAuthenticator = get()
        )
    }
    viewModel {
        SignUpViewModel(
            authRepository = get(),
            validator = get()
        )
    }

    viewModel {
        LoginViewModel(
            authRepository = get(),
            validator = get()
        )
    }
    //endregion

    //region factory
    factory { ValidatorImpl(context = get()) as Validator }
    factory {
        GoogleAuthenticator()
    }
    factory {
        FacebookAuthenticator()
    }
    //endregion

    //region db provider
    single { provideRoomDatabase(context = get()) }
    single { provideUserDao(appDatabase = get()) }
    //endregion
}