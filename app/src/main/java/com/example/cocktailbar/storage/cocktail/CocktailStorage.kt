package com.example.cocktailbar.storage.cocktail

import android.content.Context
import androidx.room.Room
import com.example.cocktailbar.entity.Cocktail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CocktailStorage(context: Context) {

    companion object {
        private const val COCKTAIL_DATABASE = "COCKTAIL_DATABASE"
    }

    private val database = Room.databaseBuilder(
        context,
        CocktailDatabase::class.java,
        COCKTAIL_DATABASE
    ).build()
    private val dao = database.cocktailDao()

    suspend fun getAll(): List<Cocktail> = withContext(Dispatchers.IO) {
        dao.getAll()
    }

    suspend fun saveAll(crimes: List<Cocktail>) = withContext(Dispatchers.IO) {
        dao.saveAll(crimes)
    }

    suspend fun save(crime: Cocktail) = withContext(Dispatchers.IO) {
        dao.save(crime)
    }

    suspend fun deleteAll() = withContext(Dispatchers.IO) {
        dao.deleteAll()
    }
    
}