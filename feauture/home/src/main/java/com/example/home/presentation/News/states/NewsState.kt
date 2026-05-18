package com.example.home.presentation.News.states

import com.example.home.presentation.News.data.ArticlesDataUi

sealed interface NewsState {
    object Idle: NewsState
    object Loading: NewsState
    data class Success(val newsList: List<ArticlesDataUi>): NewsState
    data class Error(val msg: String): NewsState
}