package com.example.worldflags.utils

import com.example.worldflags.models.FlagProperty

/** This class is only intended for displaying mock data in @Previews, using Jetpack Compose and Composable functions. */

class MockData {

    val mockFlagProperty = FlagProperty(
        name = "Sweden",
        ISOAlpha2 = "SE",
        continent = "Europe",
        capital = "Stockholm",
        type = "sovereign",
        existsToday = false,
        )

}