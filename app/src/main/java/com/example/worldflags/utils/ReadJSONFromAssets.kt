package com.example.worldflags.utils

import android.content.Context
import com.example.worldflags.models.Country
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStream

/**
 * Utils class with the only purpose (Single Responsibility Principle) of handling JSON files (file operations) and its data. */

class ReadJSONFromAssets(private val context: Context) {

    fun createAndReturnCountryObjects(): List<Country>? {
        val jsonString = readJsonFile("countries_en.json")
        val countries = jsonString?.let { parseJsonStringToCountries(it) }
        return countries
    }

    private fun readJsonFile(fileName: String): String? {
        val json: String?
        try {
            val inputStream: InputStream = context.assets.open(fileName)
            val stringBuilder = StringBuilder()
            inputStream.bufferedReader().useLines { lines ->
                lines.forEach { line ->
                    stringBuilder.append(line.trim())
                }
            }
            json = stringBuilder.toString()
        } catch (ex: Exception) {
            ex.printStackTrace()
            return null
        }
        return json
    }

    private fun parseJsonStringToCountries(json: String): List<Country>? {
        return try {
            val listType = object : TypeToken<List<Country>>() {}.type
            Gson().fromJson<List<Country>>(json, listType)
        } catch (ex: Exception) {
            ex.printStackTrace()
            null
        }
    }
}