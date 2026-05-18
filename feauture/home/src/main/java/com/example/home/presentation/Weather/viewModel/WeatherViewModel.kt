package com.example.home.presentation.Weather.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.usecases.GetWeatherUseCase
import com.example.home.presentation.Weather.mapper.toUi
import com.example.home.presentation.Weather.states.WeatherState
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class WeatherViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {

    private val _city = MutableStateFlow<String>("")

    val weatherState = _city.flatMapLatest { city ->
        flow {
            getWeatherUseCase(city)
                .onSuccess {
                    emit(WeatherState.Success(it.toUi()))
                }
                .onFailure {
                    emit(WeatherState.Error(it.message.toString()))
                }
        }.onStart {
            emit(WeatherState.Loading)
        }.flowOn(Dispatchers.Main)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), WeatherState.Idle)
    }

    fun getWeather(city: String) {
        _city.tryEmit(city)
    }

}