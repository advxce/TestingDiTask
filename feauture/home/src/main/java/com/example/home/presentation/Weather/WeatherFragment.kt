package com.example.home.presentation.Weather

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.home.databinding.FragmentNewsBinding
import com.example.home.databinding.FragmentWeatherBinding
import com.example.home.di.HomeComponent
import com.example.home.di.HomeComponentViewModel
import com.example.home.di.Routes
import com.example.home.di.Screen
import com.example.home.presentation.Weather.states.WeatherState
import com.example.home.presentation.Weather.viewModel.WeatherViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherFragment : Fragment() {

    private var _binding: FragmentWeatherBinding? = null
    private val binding
        get() = _binding!!

    private val homeComponent: HomeComponent by lazy {
        ViewModelProvider(this)[HomeComponentViewModel::class.java].homeComponent as HomeComponent
    }

    @Inject
    lateinit var weatherViewModelFactory: ViewModelProvider.Factory

    private val weatherViewModel: WeatherViewModel by viewModels {
       weatherViewModelFactory
    }
    private var route: Routes? = null

    override fun onAttach(context: Context) {
        homeComponent.inject(this@WeatherFragment)
        super.onAttach(context)
        route = (context as? Routes)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        weatherViewModel.getWeather("London")

        setupObserver()
        setupButtons()


    }


    private fun setupObserver() = with(binding) {
        viewLifecycleOwner.lifecycleScope.launch {
            weatherViewModel.weatherState.collect { state ->
                when (state) {
                    WeatherState.Idle -> {}
                    WeatherState.Loading -> {}
                    is WeatherState.Error -> {}
                    is WeatherState.Success -> {
                        tvTemperature.text = "Temperature: ${state.weather.temp_c}"
                        tvCloud.text = "Cloud: ${state.weather.cloud}"
                        tvHumidity.text = "Humidity: ${state.weather.humidity}"
                        tvLastUpdated.text = "Last updated: ${state.weather.last_updated}"
                    }
                }
            }

        }
    }

    private fun setupButtons() {
        with(binding) {
            btnGoToNews.setOnClickListener {
                route?.navigateTo(Screen.NewsScreen)
            }

            btnGoToHome.setOnClickListener {
                route?.navigateTo(Screen.HomeScreen)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        route = null
    }

}