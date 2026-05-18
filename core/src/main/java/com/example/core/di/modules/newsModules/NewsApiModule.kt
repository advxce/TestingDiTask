package com.example.core.di.modules.newsModules

import com.example.core.BuildConfig
import com.example.core.data.newsApi.NewsService
import com.example.core.di.modules.newsModules.NewsBindModule
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NewsBindModule::class])
class NewsApiModule {



    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideNewsRetrofit(
        client: OkHttpClient,
        json: Json
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()

    @Provides
    @Singleton
    fun provideNewsService(retrofit: Retrofit) =
        retrofit.create(NewsService::class.java)

    companion object{
        val NEWS_API_KEY: String = BuildConfig.NEWS_API_KEY
        const val BASE_URL = "https://newsapi.org/v2/"
    }
}