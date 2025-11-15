package com.example.beer.data.dao

import androidx.room.*
import com.example.beer.data.model.TasteModel

@Dao
interface TasteDao {
    @Insert
    suspend fun insertTaste(taste: TasteModel): Long

    @Query("SELECT * FROM tastes")
    suspend fun getAllTastes(): List<TasteModel>
}