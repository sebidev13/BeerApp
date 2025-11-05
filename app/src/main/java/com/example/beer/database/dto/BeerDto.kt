package com.example.beer.database.dto

import com.example.beer.database.entities.Beer
import com.example.beer.database.entities.BeerType
import kotlinx.serialization.Serializable as KxSerializable

@KxSerializable
data class BeerDto(
    val name: String,
    val producer: String,
    val alcoholPercentage: Double,
    val type: BeerType,
    val price: Double,
    val note: String?
)

fun Beer.toExport(): BeerDto = BeerDto(
    name = name,
    producer = producer,
    alcoholPercentage = alcoholPercentage,
    type = type,
    price = price,
    note = note
)