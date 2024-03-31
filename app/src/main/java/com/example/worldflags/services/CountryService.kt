package com.example.worldflags.services

import com.example.worldflags.models.Country

/**
 * An interface that handles all business logic, related to countries. */

interface CountryService {

    fun getAllCountries(): List<Country>?

    fun getOneRandomCountry(): Country?

    fun getFourRandomAndUniqueCountries(): List<Country>?

}