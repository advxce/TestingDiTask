package com.example.core.data.weatherApi.repository

import com.example.core.data.weatherApi.WeatherService
import com.example.core.data.weatherApi.mapper.toDomain
import com.example.core.domain.models.weather.Weather
import com.example.core.domain.repositories.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherService: WeatherService
) : WeatherRepository {
    override suspend fun getWeather(city: String): Result<Weather> {
        return try {
            val response = weatherService.getWeather(city = city)
            if (response.isSuccessful) {
                val weather =
                    response.body() ?: return Result.failure(IllegalArgumentException("empty list"))
                Result.success(weather.toDomain())
            } else {
                Result.failure(IllegalArgumentException("Empty response"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}