package com.skhkma.anidex.anime.di

import com.skhkma.anidex.anime.ui.AnimeDetailViewModel
import com.skhkma.anidex.anime.ui.AnimeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val animeViewModelModule = module {
    viewModel {
        AnimeViewModel(
            animeRepository = get()
        )
    }

    viewModelOf(::AnimeDetailViewModel)
}