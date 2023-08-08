package com.example.cocktailbar.storage.cocktail

import android.net.Uri
import androidx.core.net.toUri
import androidx.room.RawQuery
import androidx.room.TypeConverter

class IngredientsListConverter {

    companion object {
        private const val SEPARATOR = ": "
    }

    @TypeConverter
    fun toString(ingredients: List<String>): String {
        return if (ingredients.isNotEmpty()) {
            ingredients.joinToString(separator = SEPARATOR) { it }
        } else {
            ""
        }
    }

    @TypeConverter
    fun toList(rawString: String): List<String> {
        return if (rawString != "") {
            rawString.split(SEPARATOR)
        } else {
            emptyList()
        }
    }

    @TypeConverter
    fun toUri(rawString: String): Uri {
        return if (rawString != "") {
            rawString.toUri()
        } else {
            Uri.EMPTY
        }
    }

    @TypeConverter
    fun toString(uri: Uri): String {
        return uri.toString()
    }
}