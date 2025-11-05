package com.example.beer

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.beer.database.AppDatabase
import com.example.beer.databinding.ActivityMainBinding
import com.example.beer.repository.BeerExporter
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var exporter: BeerExporter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = AppDatabase.getDatabase(applicationContext)

        // Test DB Share (Einfach folgendes auskommentieren)
        val beerDao = db.beerDao()

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
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}