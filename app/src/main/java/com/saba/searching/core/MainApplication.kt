package com.saba.searching.core

import androidx.multidex.MultiDexApplication
import com.saba.searching.core.di.ApiServiceModule
import com.saba.searching.core.di.NetworkModule
import com.saba.searching.core.di.RepositoryModule
import com.saba.searching.core.di.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class MainApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
        Timber.tag("MTG").e("APP Started")


        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MainApplication)
            loadKoinModules(listOf(
                NetworkModule,
                ApiServiceModule,
                ViewModelModule,
                RepositoryModule
            ))
            koin.createRootScope()
        }
    }

}