package com.example.core.data.newsApi.mapper

import com.example.core.data.newsApi.model.NewsData
import com.example.core.domain.models.news.News

fun NewsData.toDomain() =
    News(
        articles = articles.map { it.toDomain() }
    )