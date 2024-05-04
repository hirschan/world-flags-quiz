package com.example.worldflags.services

import com.example.worldflags.models.FlagProperty

/**
 * An interface that handles all business logic, related to flags. */

interface FlagService {

    fun getAllFlags(): List<FlagProperty>?

    fun getTotalNumberOfFlags(): Int

    fun getRandomFlag(flagPropertyList: List<FlagProperty>?): FlagProperty?

    fun getFourRandomFlagPropsToDisplay(): List<FlagProperty>?

    fun setCorrectFlagProp(): FlagProperty

    fun addFlagPropToBlacklist(correctFlagProperty: FlagProperty)

}