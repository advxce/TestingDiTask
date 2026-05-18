package com.example.core.data.weatherApi.mapper

import com.example.core.data.weatherApi.model.WeatherData
import com.example.core.domain.models.weather.Weather

fun WeatherData.toDomain(): Weather{
    val weather = this.current
    return Weather(
        weather.last_updated,
        weather.temp_c,
        weather.humidity,
        weather.cloud
    )
}
