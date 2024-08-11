package com.example.worldflags.koin

import com.example.worldflags.services.DataStoreServiceImpl
import com.example.worldflags.services.FlagServiceImpl
import com.example.worldflags.services.OptionServiceImpl
import com.example.worldflags.utils.ReadJSONFromAssets
import com.example.worldflags.views.MainActivityViewModel
import com.example.worldflags.views.OptionActivityViewModel
import com.example.worldflags.views.PlayActivityViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


/**
 * For Koin dependency injection, defining dependencies and injecting them. */

val appModule = module {
    single { ReadJSONFromAssets(androidContext()) }

    factory { FlagServiceImpl(readJSONFromAssets = get(), optionService = get()) }

    single { OptionServiceImpl() }

    single { DataStoreServiceImpl() }

    factory { PlayActivityViewModel(flagService = get()) }

    single { MainActivityViewModel(optionService = get(), dataStoreService = get()) }

    single { OptionActivityViewModel(optionService = get(), dataStoreService = get()) }
}