package com.example.beer.ui.navigationbar

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.beer.data.TabItem
import com.example.beer.ui.beer.BeerTabViewModel
import com.example.beer.ui.rating.RatingTabViewModel
import com.example.beer.ui.setting.SettingsTabViewModel
import com.example.beer.ui.beer.BeerTabScreen
import com.example.beer.ui.rating.RatingTabScreen
import com.example.beer.ui.setting.SettingsTabScreen

@Composable
fun BottomTabsScreen(tabsViewModel: TabsViewModel, beerTabViewModel: BeerTabViewModel, ratingTabViewModel: RatingTabViewModel, settingsTabViewModel: SettingsTabViewModel) {
    val selectedTab by tabsViewModel.selectedTab.collectAsState()
    val content by tabsViewModel.content.collectAsState()

    Scaffold(
        bottomBar = {
            NavigationBar {
                TabItem.values().forEach { tab ->
                    NavigationBarItem(
                        selected = tab == selectedTab,
                        onClick = { tabsViewModel.selectTab(tab) },
                        label = { Text(tab.title) },
                        icon = { /*Icon(Icons.Default.Home, null) */}
                    )
                }
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize()) {
            when (selectedTab) {
                TabItem.Beer -> BeerTabScreen(viewModel = beerTabViewModel)
                TabItem.Rating -> RatingTabScreen(viewModel = ratingTabViewModel)
                TabItem.Settings -> SettingsTabScreen(viewModel = settingsTabViewModel)
            }
        }
    }
}