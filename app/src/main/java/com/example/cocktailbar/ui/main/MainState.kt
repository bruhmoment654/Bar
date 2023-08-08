package com.example.cocktailbar.ui.main

import com.example.cocktailbar.entity.Cocktail

data class MainState(

    val cocktails: List<Cocktail> = emptyList()
)

sealed class MainEffect