package com.example.worldflags.views

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

class OptionActivityViewModel(
    private val optionService: OptionServiceImpl,
    private val dataStoreService: DataStoreServiceImpl,
): ViewModel() {

    init {
        println("ALF OptionActivityViewModel initialized.")
    }

    fun changeSelectedOption(newOption: FlagCategories) {
        loadNewJsonFile(newOption)
    }

    private fun loadNewJsonFile(newOption: FlagCategories) {
        val tempFile = when (newOption) {
            FlagCategories.EUROPE_UN -> EUROPE_UN
            FlagCategories.NORTH_AMERICA_UN -> NORTH_AMERICA_UN
            FlagCategories.SOUTH_AMERICA_UN -> SOUTH_AMERICA_UN
            FlagCategories.ANTARCTICA -> AN_TERRITORIES
            FlagCategories.AFRICA_UN -> AFRICA_UN
            FlagCategories.OCEANIA_UN -> OCEANIA_UN
            FlagCategories.ASIA_UN -> ASIA_UN
        }
        optionService.changeJSONAssetFile(tempFile)
    }

    suspend fun readFromDataStore(context: Context): String {
        return dataStoreService.readFromDataStore(context)
    }

    suspend fun saveToDataStore(context: Context, value: String) {
        dataStoreService.saveToDataStore(context, value)
    }

    override fun onCleared() {
        // Perform cleanup tasks here
        super.onCleared()
    }

}