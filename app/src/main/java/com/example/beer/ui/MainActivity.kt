package com.example.beer.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.beer.ui.beer.BeerTabViewModel
import com.example.beer.ui.navigationbar.BottomTabsScreen
import com.example.beer.ui.navigationbar.TabsViewModel
import com.example.beer.ui.rating.RatingTabViewModel
import com.example.beer.ui.setting.SettingsTabViewModel
import com.example.beer.ui.theme.BeerAppTheme

class MainActivity : ComponentActivity() {

    private lateinit var beerTabViewModel: BeerTabViewModel
    private lateinit var ratingTabViewModel: RatingTabViewModel
    private lateinit var settingsTabViewModel: SettingsTabViewModel
    private lateinit var tabsViewModel: TabsViewModel


    //private lateinit var exporter: BeerExporter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //val db = AppDatabase.Companion.getDatabase(applicationContext)

        // Test DB Share (Einfach folgendes auskommentieren)
        /*val beerDao = db.beerDao()

        // 2) Exporter initialisieren
        exporter = BeerExporter(applicationContext, beerDao)

        // 3) In Coroutine aufrufen
        lifecycleScope.launch {
            runCatching { exporter.exportBeersJson() }
                .onSuccess { uri ->
                    // teilen
                    val intent = Intent(Intent.ACTION_SEND).apply {
                        type = "application/json"
                        putExtra(Intent.EXTRA_STREAM, uri)
                        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    }
                    startActivity(Intent.createChooser(intent, "Beers exportieren"))
                }
                .onFailure { e ->
                    Toast.makeText(this@MainActivity,
                        "Export fehlgeschlagen: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
                }
        }*/

        tabsViewModel = TabsViewModel()
        beerTabViewModel = BeerTabViewModel()
        ratingTabViewModel = RatingTabViewModel()
        settingsTabViewModel = SettingsTabViewModel()

        enableEdgeToEdge()
        setContent {
            BeerAppTheme {
                BottomTabsScreen(
                    tabsViewModel = tabsViewModel,
                    beerTabViewModel = beerTabViewModel,
                    ratingTabViewModel = ratingTabViewModel,
                    settingsTabViewModel = settingsTabViewModel
                )
            }
        }
    }
}