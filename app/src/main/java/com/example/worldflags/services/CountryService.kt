package com.example.worldflags.services

import com.example.worldflags.models.Country

/**
 * An interface that handles all business logic, related to countries. */

interface CountryService {

    fun getAllCountries(): List<Country>?

    fun getTotalNumberOfAllCountries(): Int

    fun getOneRandomCountryFromList(countryList: List<Country>?): Country?

    fun getFourRandomNoDuplicateCountries(): List<Country>?

    fun setCorrectCountry(): Country

    fun addCountryToBlacklist(correctCountry: Country)

}