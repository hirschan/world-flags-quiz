package com.example.worldflags.views

import androidx.lifecycle.ViewModel

enum class CountryCategories {
    EU_COUNTRIES_NON_UN,
    EU_COUNTRIES_UN,
}

class OptionActivityViewModel(): ViewModel() {

    init {
        println("ALF OptionActivityViewModel initialized.")
    }
}