package com.example.home.presentation.Weather.states

import com.example.home.presentation.Weather.model.WeatherUi

sealed interface WeatherState {
    object Idle : WeatherState
    object Loading : WeatherState
    data class Success(val weather: WeatherUi) : WeatherState
    data class Error(val message: String) : WeatherState
}
