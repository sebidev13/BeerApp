package com.example.beer.interfaces

import com.example.beer.data.model.BeerModel

interface BeerRepository {
    suspend fun getAllBeers(): List<BeerModel>
    suspend fun getBeer(id: Int): BeerModel?
    suspend fun addBeer(beer: BeerModel)
    suspend fun addBeers(beers: List<BeerModel>)
    suspend fun updateBeer(beer: BeerModel)
    suspend fun deleteBeer(beer: BeerModel)
}