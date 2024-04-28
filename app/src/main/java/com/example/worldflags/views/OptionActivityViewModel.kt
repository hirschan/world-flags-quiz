package com.example.worldflags.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.worldflags.models.JSONFiles.Companion.EUROPE_NON_UN
import com.example.worldflags.models.JSONFiles.Companion.EUROPE_UN
import com.example.worldflags.services.OptionServiceImpl

enum class CountryCategories {
    EU_COUNTRIES_NON_UN,
    EU_COUNTRIES_UN,
}

class OptionActivityViewModel(
    val optionService: OptionServiceImpl,
): ViewModel() {

    private val _selectedOption = MutableLiveData<CountryCategories>(CountryCategories.EU_COUNTRIES_NON_UN)
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
            CountryCategories.EU_COUNTRIES_UN -> EUROPE_UN
            else -> EUROPE_NON_UN
        }
        optionService.changeJSONAssetFile(tempFile)
    }

}