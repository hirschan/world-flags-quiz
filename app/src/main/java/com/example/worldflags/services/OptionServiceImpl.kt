package com.example.worldflags.services

import com.example.worldflags.models.JSONFiles.Companion.AFRICA_UN

class OptionServiceImpl(): OptionService {

    private var jsonFileName = AFRICA_UN // By default

    override fun getJSONAssetFile(): String { // TODO: read from dataStore
        return jsonFileName
    }

    override fun changeJSONAssetFile(newJsonFileName: String) {
        jsonFileName = newJsonFileName
    }
}