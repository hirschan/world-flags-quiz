package com.example.worldflags.services

import com.example.worldflags.models.Country
import com.example.worldflags.utils.ReadJSONFromAssets
import kotlin.random.Random

class CountryServiceImpl(
    readJSONFromAssets: ReadJSONFromAssets
): CountryService  {

    private var countries: List<Country>? = readJSONFromAssets.createAndReturnCountryObjects()
    private val fourRandomCountriesInSet = mutableSetOf<Country>()
    private var fourRandomAndUniqueCountries = mutableListOf<Country>()

    init {
        println("ALF CountryServiceImpl initialized.")
    }

    override fun getAllCountries(): List<Country>? {
        return countries
    }

    override fun getOneRandomCountry(): Country? {
        val temporaryCountriesList = countries
        return if (temporaryCountriesList?.isEmpty() == true) {
            null
        } else {
            val randomIndex = Random.nextInt(0, temporaryCountriesList?.size ?: 0)
            temporaryCountriesList?.get(randomIndex)
        }
    }

    override fun getFourRandomAndUniqueCountries(): List<Country> {
        while (fourRandomCountriesInSet.size < 4) {
            getOneRandomCountry()?.let { fourRandomCountriesInSet.add(it) }
        }
        fourRandomAndUniqueCountries = ArrayList(fourRandomCountriesInSet)
        return fourRandomAndUniqueCountries
    }

}