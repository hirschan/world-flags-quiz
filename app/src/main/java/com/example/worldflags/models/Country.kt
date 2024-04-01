package com.example.worldflags.models

/** Data class that holds all attributes for a country. */

data class Country(
    val name: String,
    val ISOAlpha2: String,
    val continent: String,
    val capital: String,
    val isDefinedByUN: Boolean,
    val existsToday: Boolean,
    val emojiFlag: String,
)
