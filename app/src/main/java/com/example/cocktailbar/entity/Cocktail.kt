package com.example.cocktailbar.entity

import android.net.Uri
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Cocktail(
    @PrimaryKey val id: String,
    val name: String,
    val description: String = "",
    val recipe: String = "",
    val ingredients: MutableList<String> = arrayListOf(),
    var image: Uri = Uri.EMPTY
) : Parcelable