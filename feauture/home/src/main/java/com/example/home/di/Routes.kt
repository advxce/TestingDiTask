package com.example.home.di

import androidx.fragment.app.Fragment

interface Routes {
    fun navigateTo(screen: Screen)
}

sealed class Screen{
    object HomeScreen: Screen()
    object WeatherScreen: Screen()
    object NewsScreen: Screen()
}