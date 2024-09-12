package com.skhkma.anidex.features.auth.data.di


import com.skhkma.anidex.data.repository.AuthRepository
import com.skhkma.anidex.features.auth.ui.login.viewmodel.EmailPasswordLoginViewModel
import com.skhkma.anidex.features.auth.ui.signup.viewmodel.EmailPasswordSignUpViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val authModule = module {

    viewModel {
        EmailPasswordSignUpViewModel(authRepository = get())
    }

    viewModel {
        EmailPasswordLoginViewModel(
            authRepository = get()
        )
    }
}