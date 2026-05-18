package com.example.core.data.newsApi.repository

import com.example.core.data.newsApi.NewsService
import com.example.core.data.newsApi.mapper.toDomain
import com.example.core.domain.models.news.Articles
import com.example.core.domain.models.news.News
import com.example.core.domain.repositories.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsService: NewsService
) : NewsRepository {
    override suspend fun getAllNews(): Result<News> {
        return try {
            val response = newsService.getAllNews()
            if (response.isSuccessful) {
                val news =
                    response.body() ?: return Result.failure(IllegalArgumentException("empty list"))
                if (news.articles.isEmpty()) {
                    Result.failure<Exception>(IllegalArgumentException("Empty list of news"))
                }
                Result.success(news.toDomain())
            } else {
                Result.failure(IllegalArgumentException("Empty response"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


}