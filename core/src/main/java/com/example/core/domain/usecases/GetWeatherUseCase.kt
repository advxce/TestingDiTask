package com.example.core.domain.usecases

import com.example.core.domain.models.weather.Weather
import com.example.core.domain.repositories.WeatherRepository
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

    suspend operator fun invoke(city: String): Result<Weather>{
        return weatherRepository.getWeather(city)
    }

}