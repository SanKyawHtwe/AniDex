package com.skhkma.anidex.network.di

import com.skhkma.anidex.network.datasource.AnimeRemoteDataSourceImpl
import com.skhkma.anidex.network.datasource.AnimeRemoteDatasource
import org.koin.dsl.module

val animeDataSourceModule = module {
    single {
        AnimeRemoteDataSourceImpl(
            httpClient = get()
        ) as AnimeRemoteDatasource
    }
}