package com.example.worldflags.views

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.worldflags.models.FlagCategories
import com.example.worldflags.models.JSONFiles.Companion.AFRICA_UN
import com.example.worldflags.models.JSONFiles.Companion.AN_TERRITORIES
import com.example.worldflags.models.JSONFiles.Companion.ASIA_UN
import com.example.worldflags.models.JSONFiles.Companion.EUROPE_UN
import com.example.worldflags.models.JSONFiles.Companion.NORTH_AMERICA_UN
import com.example.worldflags.models.JSONFiles.Companion.OCEANIA_UN
import com.example.worldflags.models.JSONFiles.Companion.SOUTH_AMERICA_UN
import com.example.worldflags.services.DataStoreServiceImpl
import com.example.worldflags.services.OptionServiceImpl

class MainActivityViewModel(
    private val optionService: OptionServiceImpl,
    private val dataStoreService: DataStoreServiceImpl,
): ViewModel() {

    init {
        println("ALF MainActivityViewModel initialized.")
    }

    suspend fun readFromDataStore(context: Context): String {
        return dataStoreService.readFromDataStore(context)
    }

    fun loadJsonFile(option: String?) {
        val tempFile = when (option) {
            FlagCategories.EUROPE_UN.name -> EUROPE_UN
            FlagCategories.NORTH_AMERICA_UN.name -> NORTH_AMERICA_UN
            FlagCategories.SOUTH_AMERICA_UN.name -> SOUTH_AMERICA_UN
            FlagCategories.ANTARCTICA.name -> AN_TERRITORIES
            FlagCategories.AFRICA_UN.name -> AFRICA_UN
            FlagCategories.OCEANIA_UN.name -> OCEANIA_UN
            FlagCategories.ASIA_UN.name -> ASIA_UN
            else -> { AFRICA_UN }
        }
        optionService.changeJSONAssetFile(tempFile)
    }

    override fun onCleared() {
        // Perform cleanup tasks here
        super.onCleared()
    }
}