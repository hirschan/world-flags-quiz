package com.example.worldflags.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class CountryCategories {
    EU_COUNTRIES_NON_UN,
    EU_COUNTRIES_UN,
}

class OptionActivityViewModel(): ViewModel() {

    private val _selectedOption = MutableLiveData<CountryCategories>(CountryCategories.EU_COUNTRIES_NON_UN)
    val selectedOption: LiveData<CountryCategories> = _selectedOption

    init {
        println("ALF OptionActivityViewModel initialized.")
    }

    fun changeSelectedOption(newOption: CountryCategories) {
        _selectedOption.value = newOption
    }

}