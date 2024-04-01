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

    private val _fourRandomAndUniqueCountryNames = MutableLiveData<List<Country>>()
    val fourRandomAndUniqueCountryNames: LiveData<List<Country>> = _fourRandomAndUniqueCountryNames

    private val _correctCountry = MutableLiveData<Country>()
    val correctCountry: MutableLiveData<Country> = _correctCountry

    init {
        println("ALF PlayActivityViewModel initialized.")
        generateFourNewCountryNames()
        setNewCorrectCountry()
    }

    private fun generateFourNewCountryNames() {
        _fourRandomAndUniqueCountryNames.value = emptyList()

        val tempFourRandomAndUniqueCountryNames = mutableListOf<Country>()
        val fourRandomAndUniqueCountries = countryService.getFourRandomAndUniqueCountries()
        for (countryName in fourRandomAndUniqueCountries) {
            tempFourRandomAndUniqueCountryNames.add(countryName)
        }
        _fourRandomAndUniqueCountryNames.value = tempFourRandomAndUniqueCountryNames
    }

    private fun setNewCorrectCountry() {
        val randomIndex = Random.nextInt(0, 4)
        _correctCountry.value = _fourRandomAndUniqueCountryNames.value?.get(randomIndex)
    }

    fun getCorrectCountry(): Country? {
        return _correctCountry.value
    }

    fun onCorrectAnswerSelected(correctCountry: Country) {
        countryService.addCountryToBlacklist(correctCountry)

        generateFourNewCountryNames()
        setNewCorrectCountry()
    }

}