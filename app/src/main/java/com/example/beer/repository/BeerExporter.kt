package com.example.beer.repository

import android.content.Context
import androidx.core.content.FileProvider
import com.example.beer.database.dao.BeerDao
import com.example.beer.database.dto.toExport
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

class BeerExporter(
    private val context: Context,
    private val beerDao: BeerDao
) {
    private val json = Json {
        prettyPrint = true
        encodeDefaults = false
        explicitNulls = false
    }

    /**
     * Exportiert alle Beers als JSON, gibt eine Content-URI zurück,
     * die fürs Teilen geeignet ist.
     */
    suspend fun exportBeersJson(): android.net.Uri {
        val beers = beerDao.getAllBeers().map { it.toExport() }
        val payload = json.encodeToString(beers)

        // Datei im Cache
        val outFile = File(context.cacheDir, "beers_export.json")
        outFile.writeText(payload, Charsets.UTF_8)

        // FileProvider → content:// URI
        return FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            outFile
        )
    }
}