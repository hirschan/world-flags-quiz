package com.example.worldflags.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.worldflags.models.Country
import com.example.worldflags.services.CountryServiceImpl

/**
 * An ViewModel-class that handles all UI logic, related to countries. */

class PlayActivityViewModel(
    val countryService: CountryServiceImpl,
): ViewModel() {

    private val _fourRandomNoDuplicateCountries = MutableLiveData<List<Country>>()
    val fourRandomNoDuplicateCountries: LiveData<List<Country>> = _fourRandomNoDuplicateCountries

    private val _correctCountry = MutableLiveData<Country>()
    val correctCountry: MutableLiveData<Country> = _correctCountry

    private val _isComplete = MutableLiveData<Boolean>(false)
    val isComplete: MutableLiveData<Boolean> = _isComplete

    init {
        println("ALF PlayActivityViewModel initialized.")
        generateFourNewCountries()
    }

    private fun generateFourNewCountries() {
        _fourRandomNoDuplicateCountries.value = countryService.getFourRandomCountriesToDisplay()
        setNewCorrectCountry()
    }

    private fun setNewCorrectCountry() {
        _correctCountry.value = countryService.setCorrectCountry()
    }

    fun onCorrectAnswerSelected(correctCountry: Country) {
        countryService.addCountryToBlacklist(correctCountry)

        if (countryService.blacklistedCountries.size == countryService.getTotalNumberOfAllCountries()) {
            _isComplete.value = true
        }

        generateFourNewCountries()
    }

    override fun onCleared() {
        // Perform cleanup tasks here
        super.onCleared()
    }

}