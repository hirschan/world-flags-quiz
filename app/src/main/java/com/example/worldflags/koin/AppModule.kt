package com.example.worldflags.koin

import com.example.worldflags.services.CountryServiceImpl
import com.example.worldflags.services.OptionServiceImpl
import com.example.worldflags.utils.ReadJSONFromAssets
import com.example.worldflags.views.MainActivityViewModel
import com.example.worldflags.views.OptionActivityViewModel
import com.example.worldflags.views.PlayActivityViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    // Define dependencies, using Koin framework

    single { ReadJSONFromAssets(androidContext()) }

    factory { CountryServiceImpl(readJSONFromAssets = get(), optionService = get()) } // TODO: investigate Service as Factory

    single { OptionServiceImpl() }

    factory { PlayActivityViewModel(countryService = get()) }

    single { MainActivityViewModel() }

    single { OptionActivityViewModel(optionService = get()) }
}