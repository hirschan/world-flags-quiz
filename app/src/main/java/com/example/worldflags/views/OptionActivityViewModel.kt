package com.example.worldflags.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.worldflags.models.CountryCategories
import com.example.worldflags.models.JSONFiles.Companion.AFRICA_NON_UN
import com.example.worldflags.models.JSONFiles.Companion.AFRICA_UN
import com.example.worldflags.models.JSONFiles.Companion.AN_TERRITORIES
import com.example.worldflags.models.JSONFiles.Companion.ASIA_UN
import com.example.worldflags.models.JSONFiles.Companion.EUROPE_NON_UN
import com.example.worldflags.models.JSONFiles.Companion.EUROPE_UN
import com.example.worldflags.models.JSONFiles.Companion.NORTH_AMERICA_NON_UN
import com.example.worldflags.models.JSONFiles.Companion.NORTH_AMERICA_UN
import com.example.worldflags.models.JSONFiles.Companion.OCEANIA_UN
import com.example.worldflags.models.JSONFiles.Companion.SOUTH_AMERICA_NON_UN
import com.example.worldflags.models.JSONFiles.Companion.SOUTH_AMERICA_UN
import com.example.worldflags.services.OptionServiceImpl

class OptionActivityViewModel(
    private val optionService: OptionServiceImpl,
): ViewModel() {

    private val _selectedOption = MutableLiveData<CountryCategories>(CountryCategories.EUROPE_NON_UN)
    val selectedOption: LiveData<CountryCategories> = _selectedOption

    init {
        println("ALF OptionActivityViewModel initialized.")
    }

    fun changeSelectedOption(newOption: CountryCategories) {
        _selectedOption.value = newOption
        loadNewJsonFile(newOption)
    }

    private fun loadNewJsonFile(newOption: CountryCategories) {
        val tempFile = when (newOption) {
            CountryCategories.EUROPE_UN -> EUROPE_UN
            CountryCategories.NORTH_AMERICA_UN -> NORTH_AMERICA_UN
            CountryCategories.NORTH_AMERICA_NON_UN -> NORTH_AMERICA_NON_UN
            CountryCategories.SOUTH_AMERICA_UN -> SOUTH_AMERICA_UN
            CountryCategories.SOUTH_AMERICA_NON_UN -> SOUTH_AMERICA_NON_UN
            CountryCategories.ANTARCTICA -> AN_TERRITORIES
            CountryCategories.AFRICA_UN -> AFRICA_UN
            CountryCategories.AFRICA_NON_UN -> AFRICA_NON_UN
            CountryCategories.OCEANIA_UN -> OCEANIA_UN
            CountryCategories.ASIA_UN -> ASIA_UN
            else -> EUROPE_NON_UN
        }
        optionService.changeJSONAssetFile(tempFile)
    }

}