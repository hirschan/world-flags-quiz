package com.example.worldflags.services

class OptionServiceImpl(): OptionService {

    private var jsonFileName = "europe_countries_non_UN.json" // By default

    override fun getJSONAssetFile(): String {
        return jsonFileName
    }

    override fun changeJSONAssetFile(newJsonFileName: String) {
        jsonFileName = newJsonFileName
    }
}