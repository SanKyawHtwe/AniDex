package com.skhkma.anidex.features.home.data.di

import com.skhkma.anidex.features.home.data.repository.AnimeRepository
import com.skhkma.anidex.features.home.data.repository.AnimeRepositoryImpl
import org.koin.dsl.module

val animeRepositoryModule = module {
    single {
        AnimeRepositoryImpl(
            animeRemoteDatasource = get()
        ) as AnimeRepository
    }
}