package com.example.core.di.modules.weatherModules

import com.example.core.data.interceptors.ServerInterceptor
import com.example.core.data.weatherApi.repository.WeatherRepositoryImpl
import com.example.core.domain.repositories.WeatherRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface WeatherBindModule {

    @Binds
    @Singleton
    fun bindWeatherRepository(impl: WeatherRepositoryImpl): WeatherRepository

}