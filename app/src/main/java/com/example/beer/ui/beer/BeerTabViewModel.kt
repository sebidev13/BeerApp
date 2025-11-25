package com.example.beer.ui.beer

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.beer.data.model.CalculatorData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class BeerTabViewModel @Inject constructor() : ViewModel() {

    private val _result = MutableStateFlow<CalculatorData?>(null)
    val result = _result.asStateFlow()

    private val _history = MutableStateFlow<List<CalculatorData>>(emptyList())
    val history = _history.asStateFlow()

    fun calculateSum(num1: Int, num2: Int) {
        _result.value = CalculatorData(num1, num2, num1 + num2)
    }

    fun calculate(num1: Int?, num2: Int?) {
        if (num1 != null && num2 != null) {
            val data = CalculatorData(num1, num2, num1 + num2)
            _result.value = data
            _history.value = history.value + data
        } else {
            _result.value = null
        }
    }
}
