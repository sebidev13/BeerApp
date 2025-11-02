package com.example.beer.database.entities

import androidx.room.*
import java.io.Serializable

@Entity(
    tableName = "beers",
    foreignKeys = [
        ForeignKey(
            entity = Rating::class,
            parentColumns = ["ratingId"],
            childColumns = ["ratingId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Taste::class,
            parentColumns = ["tasteId"],
            childColumns = ["tasteId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["ratingId"]), Index(value = ["tasteId"])]
)
data class Beer(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val producer: String,
    val alcoholPercentage: Double,
    val type: BeerType,
    val image: ByteArray?, // Store image as blob
    val price: Double,
    val note: String?,
    val ratingId: Int?,
    val tasteId: Int?
) : Serializable