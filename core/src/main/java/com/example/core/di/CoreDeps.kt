package com.example.core.di

import androidx.lifecycle.ViewModelProvider
import com.example.core.data.newsApi.NewsService
import com.example.core.data.weatherApi.WeatherService
import com.example.core.domain.repositories.NewsRepository
import com.example.core.domain.repositories.WeatherRepository

interface CoreDeps {
    val newsService: NewsService
    val weatherService: WeatherService

    val weatherRepository: WeatherRepository

    val newsRepository: NewsRepository


    val viewModelFactory: ViewModelProvider.Factory
}