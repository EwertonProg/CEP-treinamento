package com.ewerton.cep.core.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class CepApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CepApplication)
            modules(
                viewModelModule,
                netModule,
                apiModule,
                repositoryModule
            )
        }

    }
}