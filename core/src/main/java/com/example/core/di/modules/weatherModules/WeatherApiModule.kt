package com.example.core.di.modules.weatherModules

import com.example.core.BuildConfig
import com.example.core.data.interceptors.ServerInterceptor
import com.example.core.data.weatherApi.WeatherService
import com.example.core.di.modules.weatherModules.WeatherBindModule
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module(includes = [WeatherBindModule::class])
class WeatherApiModule {


    @Provides
    @Singleton
    @WeatherOkHttpClient
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        serverInterceptor: ServerInterceptor) =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(serverInterceptor)
            .build()

    @Provides
    @Singleton
    @WeatherRetrofit
    fun provideWeatherRetrofit(
        @WeatherOkHttpClient client: OkHttpClient,
        json: Json
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()

    @Provides
    @Singleton
    fun provideWeatherService(@WeatherRetrofit retrofit: Retrofit) =
        retrofit.create(WeatherService::class.java)
    companion object{
        val WEATHER_API_KEY: String = BuildConfig.WEATHER_API_KEY
        const val BASE_URL = "https://api.weatherapi.com/v1/"

    }
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class WeatherOkHttpClient

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class WeatherRetrofit