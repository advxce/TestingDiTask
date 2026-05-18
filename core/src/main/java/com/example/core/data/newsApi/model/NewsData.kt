package com.example.core.data.newsApi.model

import kotlinx.serialization.Serializable

@Serializable
data class NewsData(
    val articles: List<ArticlesData>
 )