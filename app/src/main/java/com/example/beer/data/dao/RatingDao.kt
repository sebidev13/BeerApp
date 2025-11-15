package com.example.beer.data.dao


import androidx.room.*
import com.example.beer.data.model.RatingModel

@Dao
interface RatingDao {
    @Insert
    suspend fun insertRating(rating: RatingModel): Long

    @Query("SELECT * FROM ratings")
    suspend fun getAllRatings(): List<RatingModel>
}