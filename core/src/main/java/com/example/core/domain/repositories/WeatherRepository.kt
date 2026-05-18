package com.example.core.domain.repositories

import com.example.core.domain.models.weather.Weather

interface WeatherRepository {

    suspend fun getWeather(city: String): Result<Weather>

}