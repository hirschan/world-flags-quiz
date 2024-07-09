package com.example.worldflags.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.worldflags.models.FlagProperty
import com.example.worldflags.services.FlagServiceImpl

/**
 * An ViewModel-class that handles all UI logic, related to flags. */

class PlayActivityViewModel(
    private val flagService: FlagServiceImpl,
): ViewModel() {

    private val _fourRandomUniqueFlagProps = MutableLiveData<List<FlagProperty>>()
    val fourRandomUniqueFlagProps: LiveData<List<FlagProperty>> = _fourRandomUniqueFlagProps

    private val _correctFlagProperty = MutableLiveData<FlagProperty>()
    val correctFlagProperty: MutableLiveData<FlagProperty> = _correctFlagProperty

    private val _isGameComplete = MutableLiveData<Boolean>(false)
    val isGameComplete: MutableLiveData<Boolean> = _isGameComplete

    private val _nbrOfGuessedFlags = MutableLiveData<Int>(0)
    val nbrOfGuessedFlags: MutableLiveData<Int> = _nbrOfGuessedFlags

    init {
        println("ALF PlayActivityViewModel initialized.")
        generateFourNewFlagProps()
    }

    private fun generateFourNewFlagProps() {
        _fourRandomUniqueFlagProps.value = flagService.getFourRandomFlagPropsToDisplay()
        setNewCorrectFlagProp()
    }

    private fun setNewCorrectFlagProp() {
        _correctFlagProperty.value = flagService.setCorrectFlagProp()
    }

    fun onCorrectAnswerSelected(correctFlagProperty: FlagProperty) {
        _nbrOfGuessedFlags.value = flagService.blacklistedFlags.size + 1
        flagService.addFlagPropToBlacklist(correctFlagProperty)

        if (flagService.blacklistedFlags.size == flagService.getTotalNumberOfFlags()) {
            _isGameComplete.value = true
        }

        generateFourNewFlagProps()
    }

    fun getNumberOfFlags(): Int {
        return flagService.getTotalNumberOfFlags()
    }

    override fun onCleared() {
        // Perform cleanup tasks here
        super.onCleared()
    }

}