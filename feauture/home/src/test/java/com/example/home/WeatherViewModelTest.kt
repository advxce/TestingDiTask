package com.example.home

import app.cash.turbine.test
import com.example.core.domain.models.weather.Weather
import com.example.core.domain.usecases.GetWeatherUseCase
import com.example.home.presentation.Weather.states.WeatherState
import com.example.home.presentation.Weather.viewModel.WeatherViewModel
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class WeatherViewModelTest {

    lateinit var viewModel: WeatherViewModel

    @get: Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getWeatherUseCase: GetWeatherUseCase  = mockk()

    @Test
    fun `when weather state is loading successful then weather state is Success`() = runTest{
        //given
        val mockWeather = Weather("", 38.6, 0, 0)
        val city = "Moscow"
        coEvery { getWeatherUseCase(city) } coAnswers {
            delay(300)
            Result.success(mockWeather)
        }

        //when
        viewModel = WeatherViewModel(getWeatherUseCase)

        //then
        viewModel.weatherState.test {
            assertEquals("Idle State", WeatherState.Idle, awaitItem())
            viewModel.getWeather(city)
            assertEquals("Loading State", WeatherState.Loading, awaitItem())
            assert(awaitItem() is WeatherState.Success)
            cancelAndIgnoreRemainingEvents()
        }
    }

}