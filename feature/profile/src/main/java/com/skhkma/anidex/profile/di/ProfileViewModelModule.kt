package com.skhkma.anidex.profile.di

import com.skhkma.anidex.data.repository.AuthRepository
import com.skhkma.anidex.data.repository.ProfileRepository
import com.skhkma.anidex.data.repository.ProfileRepositoryImpl
import org.koin.dsl.module

val profileViewModelModule = module {
    single {
        ProfileRepositoryImpl(authRemoteDataSource = get()) as ProfileRepository
    }
}