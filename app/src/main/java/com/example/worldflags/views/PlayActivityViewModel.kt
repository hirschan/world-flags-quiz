package com.example.worldflags.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.worldflags.models.Country
import com.example.worldflags.services.CountryServiceImpl
import kotlin.random.Random

/**
 * An ViewModel-class that handles all UI logic, related to countries. */

class PlayActivityViewModel(
    val countryService: CountryServiceImpl,
): ViewModel() {

    private val _fourRandomNoDuplicateCountries = MutableLiveData<List<Country>>()
    val fourRandomAndUniqueCountryNames: LiveData<List<Country>> = _fourRandomNoDuplicateCountries

    private val _correctCountry = MutableLiveData<Country>()
    val correctCountry: MutableLiveData<Country> = _correctCountry

    init {
        println("ALF PlayActivityViewModel initialized.")
        generateFourNewCountryNames()
        setNewCorrectCountry()
    }

    private fun generateFourNewCountryNames() {
        _fourRandomNoDuplicateCountries.value = countryService.getFourRandomNoDuplicateCountries()
    }

    private fun setNewCorrectCountry() {
        _correctCountry.value = countryService.setCorrectCountry()
    }

    fun onCorrectAnswerSelected(correctCountry: Country) {
        countryService.addCountryToBlacklist(correctCountry)

        if (countryService.blacklistedCountries.size == countryService.getTotalNumberOfAllCountries()) {
            println("ALFF return to main page")
        }

        generateFourNewCountryNames()
        setNewCorrectCountry()
    }

}