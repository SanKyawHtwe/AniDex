package com.skhkma.anidex.features.home.data.di

import com.skhkma.anidex.features.home.data.datasource.AnimeRemoteDataSourceImpl
import com.skhkma.anidex.features.home.data.datasource.AnimeRemoteDatasource
import org.koin.dsl.module

val animeDataSourceModule = module {
    single {
        AnimeRemoteDataSourceImpl(
            httpClient = get()
        ) as AnimeRemoteDatasource
    }
}