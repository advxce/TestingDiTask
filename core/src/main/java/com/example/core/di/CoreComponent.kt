package com.example.core.di

import com.example.core.di.modules.NetworkModule
import com.example.core.di.modules.newsModules.NewsApiModule
import com.example.core.di.modules.ViewModelModule
import com.example.core.di.modules.weatherModules.WeatherApiModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelModule::class,
        NewsApiModule::class,
        WeatherApiModule::class,
        NetworkModule::class
    ]
)
interface CoreComponent : CoreDeps {

    @Component.Builder
    interface Builder {
        fun build(): CoreComponent
    }
}