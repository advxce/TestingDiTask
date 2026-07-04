package com.example.home

import app.cash.turbine.test
import com.example.core.domain.models.news.News
import com.example.core.domain.usecases.GetAllNewsUseCase
import com.example.home.presentation.News.states.NewsState
import com.example.home.presentation.News.viewModel.NewsViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class NewsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getAllNewsUseCase = mockk<GetAllNewsUseCase>()

    private lateinit var viewModel: NewsViewModel

    @Test
    fun `when news loaded successfully then state is Success`() = runTest {
        // Given
        val mockNews = News(articles = emptyList())
        coEvery { getAllNewsUseCase() } coAnswers{
            delay(500)
            Result.success(mockNews)
        }

        // When
        viewModel = NewsViewModel(getAllNewsUseCase)

        // Then
        viewModel.news.test {
//            assertEquals("Начальное состояние должно быть Idle", NewsState.Idle, awaitItem())
            assertEquals("Затем должно пойти Loading", NewsState.Loading, awaitItem())
            
            val state = awaitItem()
            assert(state is NewsState.Success)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `when news loading fails then state is Error`() = runTest {
        // 1. Подготовка
        val errorMessage = "Network Error"
        coEvery { getAllNewsUseCase() } coAnswers {
            delay(500) // Искусственная задержка, чтобы Loading успел отобразиться
            Result.failure(Exception(errorMessage))
        }

        // 2. Действие
        viewModel = NewsViewModel(getAllNewsUseCase)

        // 3. Проверка
        viewModel.news.test {
//            assertEquals("Ожидаем Idle", NewsState.Idle, awaitItem())
            assertEquals("Ожидаем Loading", NewsState.Loading, awaitItem())

            val finalState = awaitItem()
            assertEquals(
                "Финальное состояние должно быть Error с правильным сообщением",
                NewsState.Error(errorMessage),
                finalState
            )

            cancelAndIgnoreRemainingEvents()
        }
    }
}
