package com.example.worldflags.models

/** Data class that holds all attributes for a flag. */

data class Country( // TODO: re-name to State, Territory, FlagProperty?
    val name: String,
    val ISOAlpha2: String,
    val continent: String,
    val capital: String,
    val type: String,
    val existsToday: Boolean,
)
