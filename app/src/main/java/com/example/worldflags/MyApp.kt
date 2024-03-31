package com.example.worldflags

import android.app.Application
import com.example.worldflags.koin.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()

        // Start Koin
        startKoin {
            // Android context
            androidContext(this@MyApp)
            // Modules
            modules(listOf(appModule))
        }
    }
}