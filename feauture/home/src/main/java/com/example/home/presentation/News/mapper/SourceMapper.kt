package com.example.home.presentation.News.mapper

import com.example.core.domain.models.news.Source
import com.example.home.presentation.News.data.SourceUi

fun Source.toUi() =
    SourceUi(
        id,
        name
    )