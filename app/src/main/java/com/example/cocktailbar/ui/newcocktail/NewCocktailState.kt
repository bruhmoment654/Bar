package com.example.cocktailbar.ui.newcocktail

import android.net.Uri
import androidx.annotation.StringRes

data class NewCocktailState(
    val id: String? = null,
    val name: String = "",
    val description: String = "",
    val recipe: String = "",
    val ingredients: MutableList<String> = arrayListOf(),
    var image: Uri = Uri.EMPTY
)

sealed class NewIngredientEvent {
    object GoBack : NewIngredientEvent()
    data class SetTitleError(@StringRes val resId: Int) : NewIngredientEvent()
    data class SetDescriptionError(@StringRes val resId: Int) : NewIngredientEvent()
}