package com.example.core.data.weatherApi.model

import kotlinx.serialization.Serializable

@Serializable
data class WeatherData(
    val current: CurrentData
)
