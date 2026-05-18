package com.example.core.data.weatherApi

import com.example.core.data.weatherApi.model.WeatherData
import com.example.core.di.modules.weatherModules.WeatherApiModule.Companion.WEATHER_API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("current.json?aqi=no")
    suspend fun getWeather(
        @Query("key") api: String = WEATHER_API_KEY,
        @Query("q") city: String,
        @Query("aqi") aqi: String = "no",

    ): Response<WeatherData>

}