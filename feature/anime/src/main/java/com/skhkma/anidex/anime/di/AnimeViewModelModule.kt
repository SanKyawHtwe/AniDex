package com.skhkma.anidex.anime.di

import com.skhkma.anidex.anime.AnimeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val animeViewModelModule = module {
    viewModel {
        AnimeViewModel(
            animeRepository = get()
        )
    }
}