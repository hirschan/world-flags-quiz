package com.example.worldflags.utils

import android.content.Context
import com.example.worldflags.models.FlagProperty
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStream

/**
 * Utils class with the only purpose (Single Responsibility Principle) of handling JSON file operations and its data. */

class ReadJSONFromAssets(private val context: Context) {

    fun createAndReturnFlagPropObjects(jsonFileName: String): List<FlagProperty>? {
        val jsonString = readJsonFile(jsonFileName)
        val flags = jsonString?.let { parseJsonStringToFlagProps(it) }
        return flags
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

    private fun parseJsonStringToFlagProps(json: String): List<FlagProperty>? {
        return try {
            val listType = object : TypeToken<List<FlagProperty>>() {}.type
            Gson().fromJson<List<FlagProperty>>(json, listType)
        } catch (ex: Exception) {
            ex.printStackTrace()
            null
        }
    }
}