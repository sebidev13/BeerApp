package com.example.beer.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ratings")
data class Rating(
    @PrimaryKey(autoGenerate = true) val ratingId: Int = 0,
    val taste: Int,
    val look: Int,
    val drinkability: Int,
    val overallRating: Float
)