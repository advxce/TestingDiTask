package com.example.home.di

import androidx.lifecycle.ViewModelProvider
import com.example.core.data.newsApi.NewsService
import com.example.core.data.weatherApi.WeatherService
import com.example.core.domain.repositories.NewsRepository
import com.example.core.domain.repositories.WeatherRepository

interface HomeDeps {

    val newsService: NewsService

    val newsRepository: NewsRepository
    val weatherService: WeatherService

    val weatherRepository: WeatherRepository

}