package com.skhkma.anidex.network.di

import com.skhkma.anidex.network.datasource.AuthRemoteDataSource
import com.skhkma.anidex.network.datasource.AuthRemoteDataSourceImpl
import org.koin.dsl.module

val authDataSourceModule = module {
    single {
        AuthRemoteDataSourceImpl() as AuthRemoteDataSource
    }
}