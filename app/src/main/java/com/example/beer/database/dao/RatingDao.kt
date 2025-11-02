package com.example.beer.database.dao


import androidx.room.*
import com.example.beer.database.entities.Rating

@Dao
interface RatingDao {
    @Insert
    suspend fun insertRating(rating: Rating): Long

    @Query("SELECT * FROM ratings")
    suspend fun getAllRatings(): List<Rating>
}