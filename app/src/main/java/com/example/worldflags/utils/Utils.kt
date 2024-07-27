package com.example.worldflags.utils

import com.example.worldflags.R

/**
 * Utility class with helper functions, used in Activity-classes for example. */

class Utils {

    fun getResourceId(correctFlagISO: String): Int {
        val resourceId = try {
            val field = R.drawable::class.java.getField(correctFlagISO)
            field.getInt(null)
        } catch (e: Exception) {
            // Handle the case where the resource for the given country code doesn't exist
            R.drawable._missing_flag
        }
        return resourceId
    }

    fun convertFlagName(flagName: String): String {
        return flagName.replace(" ", "_")
    }
}