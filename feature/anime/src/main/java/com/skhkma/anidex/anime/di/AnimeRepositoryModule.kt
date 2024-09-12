package com.skhkma.anidex.anime.di

import com.skhkma.anidex.anime.repository.AnimeRepository
import com.skhkma.anidex.anime.repository.AnimeRepositoryImpl
import org.koin.dsl.module

val animeRepositoryModule = module {
    single {
        AnimeRepositoryImpl(
            animeRemoteDatasource = get()
        ) as AnimeRepository
    }
}