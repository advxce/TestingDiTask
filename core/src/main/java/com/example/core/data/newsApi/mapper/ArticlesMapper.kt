package com.example.core.data.newsApi.mapper

import com.example.core.data.newsApi.model.ArticlesData
import com.example.core.domain.models.news.Articles

fun ArticlesData.toDomain() =
    Articles(
        source.toDomain(),
        author,
        title,
        description,
        url,
        urlToImage,
        publishedAt,
        content
    )