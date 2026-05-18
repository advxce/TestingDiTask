package com.example.home.presentation.Weather.model

data class WeatherUi(
    val last_updated: String,
    val temp_c: Double,
    val humidity: Int,
    val cloud: Int,
)