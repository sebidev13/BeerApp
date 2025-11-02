package com.example.beer.database.dao

import androidx.room.*
import com.example.beer.database.entities.Taste

@Dao
interface TasteDao {
    @Insert
    suspend fun insertTaste(taste: Taste): Long

    @Query("SELECT * FROM tastes")
    suspend fun getAllTastes(): List<Taste>
}