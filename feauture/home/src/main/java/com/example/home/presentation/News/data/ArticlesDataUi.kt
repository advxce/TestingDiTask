package com.example.home.presentation.News.data

import com.example.core.data.newsApi.model.SourceData

data class ArticlesDataUi(
    val source: SourceUi,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val publishedAt: String,
    val content: String?
)