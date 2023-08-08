package com.example.cocktailbar.storage.cocktail

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.cocktailbar.entity.Cocktail


@Dao
interface CocktailDao {

    @Query("select * from cocktail")
    suspend fun getAll(): List<Cocktail>

    @Insert(onConflict = REPLACE)
    suspend fun save(entity: Cocktail)

    @Insert(onConflict = REPLACE)
    suspend fun saveAll(entity: List<Cocktail>)

    @Query("delete from cocktail")
    suspend fun deleteAll()
}