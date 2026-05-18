package com.example.home.presentation.Weather.mapper

import com.example.core.domain.models.weather.Weather
import com.example.home.presentation.Weather.model.WeatherUi

fun Weather.toUi() =
    WeatherUi(
        last_updated = last_updated,
        temp_c = temp_c,
        humidity = humidity,
        cloud = cloud,
    )
