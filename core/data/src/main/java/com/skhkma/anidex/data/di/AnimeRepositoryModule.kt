package com.skhkma.anidex.data.di

import com.skhkma.anidex.data.repository.AnimeRepository
import com.skhkma.anidex.data.repository.AnimeRepositoryImpl
import org.koin.dsl.module

val animeRepositoryModule = module {
    single {
        AnimeRepositoryImpl(
            animeRemoteDatasource = get()
        ) as AnimeRepository
    }
}