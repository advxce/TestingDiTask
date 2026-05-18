package com.example.home.di

import com.example.home.presentation.Home.HomeFragment
import com.example.home.presentation.News.NewsFragment
import com.example.home.presentation.News.viewModel.NewsViewModel
import com.example.home.presentation.Weather.WeatherFragment
import com.example.home.presentation.Weather.viewModel.WeatherViewModel
import dagger.Component
import javax.inject.Scope

@Component(dependencies = [HomeDeps::class], modules = [HomeBindViewModelModule::class,])
@Future
interface HomeComponent {

    fun inject(homeFragment: HomeFragment)
    fun inject(newsFragment: NewsFragment)
    fun inject(weatherFragment: WeatherFragment)


    @Component.Builder
    interface Builder{
        fun deps(homeDeps: HomeDeps): Builder
        fun build(): HomeComponent
    }
}


@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class Future