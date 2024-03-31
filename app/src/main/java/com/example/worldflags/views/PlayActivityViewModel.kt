package com.example.worldflags.views

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.worldflags.services.CountryServiceImpl
import kotlin.random.Random

/**
 * An ViewModel-class that handles all UI logic, related to countries. */

class PlayActivityViewModel(
    val countryService: CountryServiceImpl,
): ViewModel() {

    private var fourRandomAndUniqueCountryNames = mutableListOf<String>()

    private val _correctCountry = MutableLiveData<String>()
    val correctCountry: MutableLiveData<String> = _correctCountry

    init {
        println("ALF PlayActivityViewModel initialized.")
        generateFourNewCountryNames()
        setNewCorrectCountry()
    }

    private fun generateFourNewCountryNames() {
        val tempFourRandomAndUniqueCountryNames = mutableListOf<String>()
        val fourRandomAndUniqueCountries = countryService.getFourRandomAndUniqueCountries()
        for (countryName in fourRandomAndUniqueCountries) {
            tempFourRandomAndUniqueCountryNames.add(countryName.name)
        }
        fourRandomAndUniqueCountryNames = tempFourRandomAndUniqueCountryNames
    }

    fun getFourCountryNames(): MutableList<String> {
        return fourRandomAndUniqueCountryNames
    }

    private fun setNewCorrectCountry() {
        val randomIndex = Random.nextInt(0, 4)
        _correctCountry.value = fourRandomAndUniqueCountryNames[randomIndex]
    }

    fun getCorrectCountry(): String {
        return _correctCountry.value ?: "Null"
    }

    fun onCorrectAnswerSelected() {
        generateFourNewCountryNames()
        setNewCorrectCountry()
    }

}