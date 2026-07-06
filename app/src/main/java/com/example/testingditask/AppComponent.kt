package com.example.testingditask

import androidx.lifecycle.ViewModelProvider
import com.example.core.di.CoreDeps
import com.example.core.data.newsApi.NewsService
import com.example.core.data.weatherApi.WeatherService
import com.example.core.di.CoreComponent
import com.example.core.domain.repositories.NewsRepository
import com.example.core.domain.repositories.WeatherRepository
import com.example.home.di.HomeDeps
import com.example.testingditask.liveCodding.CarComponent
import dagger.Component
import javax.inject.Singleton
import javax.inject.Scope


@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class AppScope
@AppScope
@Component(modules = [AppModule::class], dependencies = [CoreComponent::class, CarComponent::class])
interface AppComponent: CoreDeps, HomeDeps {
    override val newsService: NewsService
    override val weatherService: WeatherService

    override val newsRepository: NewsRepository
    override val weatherRepository: WeatherRepository

    override val viewModelFactory: ViewModelProvider.Factory


    fun inject(activity: MainActivity)

    @Component.Builder
    interface Builder{
        fun coreComponent(coreComponent: CoreComponent): Builder
        fun carComponent(carComponent: CarComponent): Builder
        fun build(): AppComponent
    }

}