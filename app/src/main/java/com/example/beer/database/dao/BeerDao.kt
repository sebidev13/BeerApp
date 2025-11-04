package com.example.beer.database.dao

import androidx.room.*
import com.example.beer.database.entities.Beer

@Dao
interface BeerDao {
    @Insert
    suspend fun insertBeer(beer: Beer)

    @Update
    suspend fun updateBeer(beer: Beer)

    @Delete
    suspend fun deleteBeer(beer: Beer)

    @Query("SELECT * FROM beers ORDER BY name")
    fun getAllBeers(): List<Beer>

    @Transaction
    @Query("SELECT * FROM beers WHERE id = :id")
    suspend fun getBeerById(id: Int): Beer?
}