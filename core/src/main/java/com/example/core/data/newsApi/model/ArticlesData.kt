package com.example.core.data.newsApi.model

import kotlinx.serialization.Serializable

@Serializable
data class ArticlesData(
    val source: SourceData,
    val author: String?,
    val title: String,
    val description: String?,
    val url:String,
    val urlToImage:String?,
    val publishedAt:String,
    val content:String?

)

