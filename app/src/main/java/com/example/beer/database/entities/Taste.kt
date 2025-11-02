package com.example.beer.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tastes")
data class Taste(
    @PrimaryKey(autoGenerate = true) val tasteId: Int = 0,
    val sweetness: String,
    val bitterness: String,
    val mouthfeel: String,
    val aftertaste: String,
    val note: String?
)