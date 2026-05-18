package com.example.core.data.newsApi

import com.example.core.data.newsApi.model.NewsData
import com.example.core.di.modules.newsModules.NewsApiModule.Companion.NEWS_API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("top-headlines")
    suspend fun getAllNews(
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String = NEWS_API_KEY
    ): Response<NewsData>

}