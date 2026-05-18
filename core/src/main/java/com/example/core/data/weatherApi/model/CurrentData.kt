package com.example.core.data.weatherApi.model

import kotlinx.serialization.Serializable

@Serializable
data class CurrentData(
    val last_updated: String,
    val temp_c: Double,
    val humidity: Int,
    val cloud: Int,
)
