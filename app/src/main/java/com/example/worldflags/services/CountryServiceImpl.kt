package com.example.worldflags.services

import androidx.lifecycle.MutableLiveData
import com.example.worldflags.models.Country
import com.example.worldflags.utils.ReadJSONFromAssets
import kotlin.random.Random

class CountryServiceImpl(
    readJSONFromAssets: ReadJSONFromAssets
): CountryService  {

    private var _countries: List<Country>? = readJSONFromAssets.createAndReturnCountryObjects()
    private var _fourRandomNoDuplicateCountries = mutableListOf<Country>()

    private var _blacklistedCountries = mutableListOf<Country>()
    val blacklistedCountries: MutableList<Country> = _blacklistedCountries

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

    private fun getRandomWhitelistCountry(): Country? {
        val whitelistOfCountries = _countries?.minus(_blacklistedCountries)
        if (whitelistOfCountries?.size?.equals(0) == true) {
            return null
        }
        val randomWhitelistedCountry = whitelistOfCountries?.random()
        return randomWhitelistedCountry
    }

    override fun getFourRandomNoDuplicateCountries(): List<Country> {
        _fourRandomNoDuplicateCountries = emptyList<Country>().toMutableList()
        val fourRandomCountriesInSet = mutableSetOf<Country>()

        val correctCountry = getRandomWhitelistCountry()
        if (correctCountry != null) {
            fourRandomCountriesInSet.add(correctCountry)
        }

        while (fourRandomCountriesInSet.size < 4) {
            getOneRandomCountryFromList(_countries)?.let { fourRandomCountriesInSet.add(it) }
        }
        _fourRandomNoDuplicateCountries = ArrayList(fourRandomCountriesInSet)
        return _fourRandomNoDuplicateCountries.shuffled()
    }

    override fun setCorrectCountry(): Country {
        val randomIndex = Random.nextInt(0, 4)
        return _fourRandomNoDuplicateCountries[0]
    }

    override fun addCountryToBlacklist(correctCountry: Country) {
        _blacklistedCountries.add(correctCountry)
    }

}