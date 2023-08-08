package com.example.cocktailbar.ui.newcocktail

import android.app.Application
import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.example.cocktailbar.App
import com.example.cocktailbar.R
import com.example.cocktailbar.entity.Cocktail
import com.example.cocktailbar.ui.common.BaseViewModel
import com.example.cocktailbar.ui.newcocktail.NewIngredientEvent.*
import kotlinx.coroutines.launch
import java.util.UUID

class NewCocktailViewModel(application: Application) :
    BaseViewModel<NewCocktailState, NewIngredientEvent>(application) {

    override val initialState = NewCocktailState()

    private val cocktailStorage = (application as App).cocktailStorage
    private val photoStorage = (application as App).photoStorage

    fun updateName(name: String) {
        setState { copy(name = name) }
    }

    fun updateRecipe(recipe: String) {
        setState { copy(recipe = recipe) }
    }

    fun updateDescription(description: String) {
        setState { copy(description = description) }
    }

    fun updateImage(image: Uri) {
        setState { copy(image = image) }
    }


    fun save() = viewModelScope.launch {
        if (validate()) {
            val id = state.value.id.takeIf { it != null } ?: UUID.randomUUID().toString()

            val cocktail = Cocktail(
                id = id,
                name = state.value.name,
                description = state.value.description,
                recipe = state.value.recipe,
                ingredients = state.value.ingredients,
                image = state.value.image
            )
            cocktailStorage.save(cocktail)
            GoBack.emit()
        }
    }

    private fun validate(): Boolean {
        if (state.value.name.isEmpty()) {
            SetTitleError(R.string.field_must_filled)
            return false
        }
        return true
    }
}