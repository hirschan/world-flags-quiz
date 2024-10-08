package com.example.worldflags.services

import androidx.compose.ui.platform.LocalContext
import com.example.worldflags.models.JSONFiles.Companion.AFRICA_UN

class OptionServiceImpl(): OptionService {

    private var jsonFileName = AFRICA_UN // By default

    override fun getJSONAssetFile(): String {
        return jsonFileName
    }

    override fun changeJSONAssetFile(newJsonFileName: String) {
        jsonFileName = newJsonFileName
    }
}