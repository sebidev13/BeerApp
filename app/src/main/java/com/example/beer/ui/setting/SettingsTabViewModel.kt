package com.example.beer.ui.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beer.repository.JsonFileManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class SettingsTabViewModel @Inject constructor(
    private val jsonFileManager: JsonFileManager
) : ViewModel() {

    fun export() {
        viewModelScope.launch {

        }
    }

    fun import() {
        viewModelScope.launch {
        }
    }

    fun infos() {
    }
}