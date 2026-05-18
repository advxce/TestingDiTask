package com.example.core.data.newsApi.mapper

import com.example.core.data.newsApi.model.SourceData
import com.example.core.domain.models.news.Source

fun SourceData.toDomain() =
    Source(
        id,
        name
    )