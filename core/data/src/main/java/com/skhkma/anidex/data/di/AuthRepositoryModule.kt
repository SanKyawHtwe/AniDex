package com.skhkma.anidex.data.di

import com.skhkma.anidex.data.repository.AuthRepository
import com.skhkma.anidex.data.repository.AuthRepositoryImpl
import org.koin.dsl.module

val authRepositoryModule = module {
    single {
        AuthRepositoryImpl(authRemoteDataSource = get()) as AuthRepository
    }
}