package com.skhkma.anidex

import android.app.Application
import com.skhkma.anidex.network.di.networkModule
import com.skhkma.anidex.features.auth.data.di.authModule
import com.skhkma.anidex.data.di.animeRepositoryModule
import com.skhkma.anidex.anime.di.animeViewModelModule
import com.skhkma.anidex.data.di.authRepositoryModule
import com.skhkma.anidex.network.di.animeDataSourceModule
import com.skhkma.anidex.network.di.authDataSourceModule
import com.skhkma.anidex.profile.di.profileViewModelModule
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
                authModule,
                authDataSourceModule,
                authRepositoryModule,
                profileViewModelModule
            )
            androidContext(androidContext = this@AniDexApplication)
        }
    }
}