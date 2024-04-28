package com.example.worldflags.services

/**
 * An interface that handles all business logic, related to options and settings in the app. */

interface OptionService {

    fun getJSONAssetFile(): String

    fun changeJSONAssetFile(newJsonFileName: String)

}