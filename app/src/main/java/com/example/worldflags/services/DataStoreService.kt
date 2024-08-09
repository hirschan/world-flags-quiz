package com.example.worldflags.services

import android.content.Context

/**
 * An interface that handles business logic related to reading and saving to a device's data store. */

interface DataStoreService {

    suspend fun readFromDataStore(context: Context): String

    suspend fun saveToDataStore(context: Context, value: String)

}