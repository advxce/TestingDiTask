package com.example.home.presentation.News.mapper

import com.example.core.data.newsApi.model.ArticlesData
import com.example.core.domain.models.news.News
import com.example.home.presentation.News.data.ArticlesDataUi

fun News.toUi() : List<ArticlesDataUi>{
    return this.articles.map { article ->
        ArticlesDataUi(
            article.source.toUi(),
            article.author,
            article.title,
            article.description,
            article.url,
            article.publishedAt,
            article.content
        )
    }
}