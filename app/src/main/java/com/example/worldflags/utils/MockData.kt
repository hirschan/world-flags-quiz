package com.example.worldflags.utils

import com.example.worldflags.models.Country

/** This class is only intended for displaying mock data in @Previews, using Jetpack Compose and Composable functions. */

class MockData {

    val mockCountry = Country(
        name = "Mock",
        ISOAlpha2 = "MO",
        continent = "Mock",
        capital = "Mock City",
        isDefinedByUN = false,
        existsToday = false,
        emojiFlag = "\uD83C\uDDF8\uD83C\uDDEA",
    )

}