package com.example.worldflags.models

/** Data class that holds all attributes for a country. */

data class Country(
    val name: String,
    val ISOAlpha2: String,
    val continent: String,
    val capital: String,
    val type: String, // TODO: add ALLIANCE, COUNTRY, US_STATE, REGION as different types?
    val isSovereign: Boolean,
    val isUNMember: Boolean,
    val existsToday: Boolean,
)
