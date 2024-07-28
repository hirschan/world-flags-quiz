package com.example.worldflags.models

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

object PreferencesKeys {
    val FLAG_OPTION = stringPreferencesKey("flagOption")
}

// TODO: add doc about class
class DataStorePreferences {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    /** Write data to the DataStore */
    suspend fun saveToDataStore(context: Context, value: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.FLAG_OPTION] = value
        }
    }

    /** Read data from the DataStore */
    suspend fun readFromDataStore(context: Context): String {
        val preferences = context.dataStore.data.first()
        return preferences[PreferencesKeys.FLAG_OPTION] ?: "default_value"
    }

}