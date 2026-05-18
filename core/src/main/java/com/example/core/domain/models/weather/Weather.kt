package com.example.core.domain.models.weather

data class Weather (
    val last_updated: String,
    val temp_c: Double,
    val humidity: Int,
    val cloud: Int,
)