package com.skhkma.anidex.network.di

import com.skhkma.anidex.network.KtorUtils
import org.koin.dsl.module

val networkModule = module {
    single {
        KtorUtils.createKtor()
    }
}