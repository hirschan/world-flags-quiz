package com.example.worldflags.models

/** Data class that holds all attributes for a flag. */

data class Country( // TODO: re-name to CountryFlag, Flag?
    val name: String,
    val ISOAlpha2: String,
    val continent: String,
    val capital: String,
    val type: String, //TODO: define all types for flags could be: ALLIANCE, COUNTRY, US_STATE, REGION
    val isSovereign: Boolean, // TODO: add parentState?
    val isUNMember: Boolean,
    val existsToday: Boolean,
)
