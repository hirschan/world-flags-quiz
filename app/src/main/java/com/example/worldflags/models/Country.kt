package com.example.worldflags.models

/** Data class that holds all attributes for a flag. */

data class Country( // TODO: re-name to CountryFlag, Flag?
    val name: String,
    val ISOAlpha2: String,
    val continent: String,
    val capital: String,
    val type: String, // Other types for flags could be: ALLIANCE, COUNTRY, US_STATE, REGION
    val isSovereign: Boolean,
    val isUNMember: Boolean,
    val existsToday: Boolean,
)
