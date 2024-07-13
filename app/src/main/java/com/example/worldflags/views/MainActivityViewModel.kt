package com.example.worldflags.views

import androidx.lifecycle.ViewModel

class MainActivityViewModel(): ViewModel() {

    init {
        println("ALF MainActivityViewModel initialized.")
    }

    override fun onCleared() {
        // Perform cleanup tasks here
        super.onCleared()
    }
}