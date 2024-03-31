package com.example.worldflags.koin

import com.example.worldflags.services.CountryServiceImpl
import com.example.worldflags.utils.ReadJSONFromAssets
import com.example.worldflags.views.MainActivityViewModel
import com.example.worldflags.views.PlayActivityViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    // Define dependencies, using Koin framework

    single { ReadJSONFromAssets(androidContext()) }

    single { CountryServiceImpl(readJSONFromAssets = get()) }

    single { PlayActivityViewModel(countryService = get()) } // TODO: investigate Singleton for VM, bet practices?

    single { MainActivityViewModel() }
}