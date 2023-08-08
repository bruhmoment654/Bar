package com.example.cocktailbar.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.cocktailbar.App
import com.example.cocktailbar.ui.common.BaseViewModel
import kotlinx.coroutines.launch

class MainViewModel(application: Application) :
    BaseViewModel<MainState, MainEffect>(application) {

    private val storage = (application as App).cocktailStorage

    override val initialState: MainState = MainState()

    fun fetchCocktails() = viewModelScope.launch {
        val data = storage.getAll()
        Log.w("WWWWW", data.toString())
        setState { copy(cocktails = data) }
    }
}