package com.example.worldflags.services

import com.example.worldflags.models.FlagProperty
import com.example.worldflags.utils.ReadJSONFromAssets
import kotlin.random.Random

class FlagServiceImpl(
    readJSONFromAssets: ReadJSONFromAssets,
    optionService: OptionServiceImpl,
): FlagService  {

    private var jsonFileName = optionService.getJSONAssetFile()
    private var listOfFlags: List<FlagProperty>? = readJSONFromAssets.createAndReturnFlagPropObjects(jsonFileName)
    private var _fourRandomNoDuplicateFlagProps = mutableListOf<FlagProperty>()

    private var _blacklistedFlags = mutableListOf<FlagProperty>()
    val blacklistedFlags: MutableList<FlagProperty> = _blacklistedFlags

    init {
        println("ALF CountryServiceImpl initialized.")
    }

    override fun getAllFlags(): List<FlagProperty>? {
        return listOfFlags
    }

    override fun getTotalNumberOfFlags(): Int {
        return listOfFlags?.size ?: 0
    }

    override fun getRandomFlag(flagPropertyList: List<FlagProperty>?): FlagProperty? {
        val temporaryFlagsList = flagPropertyList
        return if (temporaryFlagsList?.isEmpty() == true) {
            null
        } else {
            val randomIndex = Random.nextInt(0, temporaryFlagsList?.size ?: 0)
            temporaryFlagsList?.get(randomIndex)
        }
    }

    private fun getRandomWhitelistFlagProp(): FlagProperty? {
        val whitelistOfFlags = listOfFlags?.minus(_blacklistedFlags)
        if (whitelistOfFlags?.size?.equals(0) == true) {
            return null
        }
        val randomWhitelistedFlagProp = whitelistOfFlags?.random()
        return randomWhitelistedFlagProp
    }

    override fun getFourRandomFlagPropsToDisplay(): List<FlagProperty> {
        _fourRandomNoDuplicateFlagProps = emptyList<FlagProperty>().toMutableList()
        val fourRandomFlagPropsInSet = mutableSetOf<FlagProperty>()

        val correctFlag = getRandomWhitelistFlagProp()
        if (correctFlag != null) {
            fourRandomFlagPropsInSet.add(correctFlag)
        }

        while (fourRandomFlagPropsInSet.size < 4) {
            getRandomFlag(listOfFlags)?.let { fourRandomFlagPropsInSet.add(it) }
        }
        _fourRandomNoDuplicateFlagProps = ArrayList(fourRandomFlagPropsInSet)
        return _fourRandomNoDuplicateFlagProps.shuffled()
    }

    override fun setCorrectFlagProp(): FlagProperty {
        return _fourRandomNoDuplicateFlagProps[0]
    }

    override fun addFlagPropToBlacklist(correctFlagProperty: FlagProperty) {
        _blacklistedFlags.add(correctFlagProperty)
    }

}