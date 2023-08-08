package com.example.cocktailbar.storage.cocktail

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cocktailbar.entity.Cocktail


@Database(entities = [Cocktail::class], version = 1)
@TypeConverters(IngredientsListConverter::class)
abstract class CocktailDatabase : RoomDatabase() {

    abstract fun cocktailDao(): CocktailDao
}