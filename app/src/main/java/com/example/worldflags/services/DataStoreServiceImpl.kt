package com.example.worldflags.services

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.worldflags.models.FlagCategories
import kotlinx.coroutines.flow.first

object PreferencesKeys {
    val FLAG_OPTION = stringPreferencesKey("flag_option")
}

class DataStoreServiceImpl(): DataStoreService {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    override suspend fun readFromDataStore(context: Context): String {
        val preferences = context.dataStore.data.first()
        return preferences[PreferencesKeys.FLAG_OPTION] ?: FlagCategories.AFRICA_UN.name
    }

    override suspend fun saveToDataStore(context: Context, value: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.FLAG_OPTION] = value
        }
    }
}