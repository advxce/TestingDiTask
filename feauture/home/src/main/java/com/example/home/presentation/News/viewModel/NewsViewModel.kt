package com.example.home.presentation.News.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.usecases.GetAllNewsUseCase
import com.example.home.presentation.News.data.ArticlesDataUi
import com.example.home.presentation.News.mapper.toUi
import com.example.home.presentation.News.states.NewsState
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class NewsViewModel @Inject constructor(
    private val getAllNewsUseCase: GetAllNewsUseCase
) : ViewModel() {

    val news: Flow<NewsState> = flow {
        getAllNewsUseCase()
            .onSuccess { news ->
            emit(NewsState.Success(news.toUi()))
        }.onFailure { exception ->
            emit(NewsState.Error(exception.message.orEmpty()))
        }
    }.flowOn(Dispatchers.Main)
        .onStart {
            emit(NewsState.Loading)
        }
        .stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = NewsState.Idle
        )


}