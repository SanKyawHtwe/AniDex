package com.skhkma.anidex.features.home.data.di

import com.skhkma.anidex.features.home.ui.viewmodel.AnimeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val animeViewModelModule = module {
    viewModel {
        AnimeViewModel(
            animeRepository = get()
        )
    }
}