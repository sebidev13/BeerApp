package com.example.beer.repository

import com.example.beer.data.dao.BeerDao
import com.example.beer.data.model.BeerModel
import com.example.beer.interfaces.BeerRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BeerRepositoryImpl @Inject constructor(
    private val beerDao: BeerDao
) : BeerRepository {

    override suspend fun getAllBeers(): List<BeerModel> =
        beerDao.getAllBeers()

    override suspend fun getBeer(id: Int): BeerModel? =
        beerDao.getBeerById(id)

    override suspend fun addBeer(beer: BeerModel) {
        beerDao.insertBeer(beer)
    }

    override suspend fun updateBeer(beer: BeerModel) {
        beerDao.updateBeer(beer)
    }

    override suspend fun deleteBeer(beer: BeerModel) {
        beerDao.deleteBeer(beer)
    }
}