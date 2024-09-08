package com.skhkma.anidex

import android.app.Application
import com.skhkma.anidex.common.di.networkModule
import com.skhkma.anidex.features.auth.data.di.authModule
import com.skhkma.anidex.features.home.data.di.animeDataSourceModule
import com.skhkma.anidex.features.home.data.di.animeRepositoryModule
import com.skhkma.anidex.features.home.data.di.animeViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AniDexApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                networkModule,
                animeDataSourceModule,
                animeRepositoryModule,
                animeViewModelModule,
                authModule
            )
            androidContext(androidContext = this@AniDexApplication)
        }
    }
}