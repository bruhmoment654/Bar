package com.example.cocktailbar

import android.app.Application
import com.example.cocktailbar.entity.Cocktail
import com.example.cocktailbar.storage.cocktail.CocktailStorage
import com.example.cocktailbar.storage.photo.PhotoStorage
import com.google.android.material.color.DynamicColors
import kotlinx.coroutines.coroutineScope

class App : Application() {

    val cocktailStorage by lazy { CocktailStorage(applicationContext) }
    val photoStorage by lazy {PhotoStorage(applicationContext)}

    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}