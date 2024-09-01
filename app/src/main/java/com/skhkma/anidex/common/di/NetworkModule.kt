package com.skhkma.anidex.common.di

import com.skhkma.anidex.features.home.data.service.KtorUtils
import org.koin.dsl.module

val networkModule = module {
    single {
        KtorUtils.createKtor()
    }
}