package com.example.worldflags.utils

import com.example.worldflags.models.Country

/** This class is only intended for displaying mock data in @Previews, using Jetpack Compose and Composable functions. */

class MockData {

    val mockCountry = Country(
        name = "Sweden",
        ISOAlpha2 = "SE",
        continent = "Europe",
        capital = "Stockholm",
        type = "country",
        isSovereign = true,
        isUNMember = true,
        existsToday = false,
        )

}