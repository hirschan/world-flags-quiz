package com.example.worldflags.services

import com.example.worldflags.models.Country
import com.example.worldflags.utils.ReadJSONFromAssets
import kotlin.random.Random

class CountryServiceImpl(
    readJSONFromAssets: ReadJSONFromAssets
): CountryService  {

    private var _countries: List<Country>? = readJSONFromAssets.createAndReturnCountryObjects()
    private var _fourRandomAndUniqueCountries = mutableListOf<Country>()

    private var _blacklistedCountries = mutableListOf<Country>()
    private var _countriesLeftToGuess = mutableListOf<Country>()

    private var _fourCountriesIncludingOneUniqueCorrect = mutableMapOf<Country, Boolean>()

    init {
        println("ALF CountryServiceImpl initialized.")
    }

    override fun getAllCountries(): List<Country>? {
        return _countries
    }

    override fun getTotalNumberOfAllCountries(): Int {
        return _countries?.size ?: 0
    }

    override fun getOneRandomCountryFromList(countryList: List<Country>?): Country? {
        val temporaryCountriesList = countryList
        return if (temporaryCountriesList?.isEmpty() == true) {
            null
        } else {
            val randomIndex = Random.nextInt(0, temporaryCountriesList?.size ?: 0)
            temporaryCountriesList?.get(randomIndex)
        }
    }

    override fun getOneRandomCountryNotFromBlacklist(): Country? {
        _countriesLeftToGuess = _countries?.filter { ! _blacklistedCountries.contains(it) }?.toMutableList() ?: mutableListOf()
        val correctCountry = getOneRandomCountryFromList(_countriesLeftToGuess)
        return correctCountry
    }

    override fun getFourRandomAndUniqueCountries(): List<Country> { // Three
        val fourRandomCountriesInSet = mutableSetOf<Country>()
        while (fourRandomCountriesInSet.size < 4) {
            getOneRandomCountryFromList(_countries)?.let { fourRandomCountriesInSet.add(it) }
        }
        _fourRandomAndUniqueCountries = ArrayList(fourRandomCountriesInSet)
        return _fourRandomAndUniqueCountries
    }

    override fun addCountryToBlacklist(correctCountry: Country) {
        _blacklistedCountries.add(correctCountry)

        if (_blacklistedCountries.size == getTotalNumberOfAllCountries()) {
            println("ALFF return to main page")
        }
    }

}