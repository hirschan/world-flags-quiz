package com.example.worldflags.services

import com.example.worldflags.models.Country

/**
 * An interface that handles all business logic, related to countries. */

interface CountryService {

    fun getAllCountries(): List<Country>?

    fun getTotalNumberOfAllCountries(): Int

    fun getRandomCountry(countryList: List<Country>?): Country?

    fun getFourRandomCountriesToDisplay(): List<Country>?

    fun setCorrectCountry(): Country

    fun addCountryToBlacklist(correctCountry: Country)

}