package com.skhkma.anidex.features.auth.data.di

import com.skhkma.anidex.features.auth.data.datasource.AuthRemoteDataSource
import com.skhkma.anidex.features.auth.data.datasource.AuthRemoteDataSourceImpl
import com.skhkma.anidex.features.auth.data.repository.AuthRepository
import com.skhkma.anidex.features.auth.data.repository.AuthRepositoryImpl
import com.skhkma.anidex.features.auth.ui.screen.login.viewmodel.EmailPasswordLoginViewModel
import com.skhkma.anidex.features.auth.ui.screen.signup.viewmodel.EmailPasswordSignUpViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val authModule = module {
    single {
        AuthRemoteDataSourceImpl() as AuthRemoteDataSource
    }

    single {
        AuthRepositoryImpl(authRemoteDataSource = get()) as AuthRepository
    }

    viewModel {
        EmailPasswordSignUpViewModel(authRepository = get())
    }

    viewModel {
        EmailPasswordLoginViewModel(
            authRepository = get()
        )
    }
}