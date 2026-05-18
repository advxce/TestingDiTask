package com.example.core.domain.repositories

import com.example.core.domain.models.news.News

interface NewsRepository {

    suspend fun getAllNews(): Result<News>

}