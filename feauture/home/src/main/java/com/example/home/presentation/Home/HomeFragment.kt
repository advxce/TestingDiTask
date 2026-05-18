package com.example.home.presentation.Home

import android.R
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.home.databinding.FragmentHomeBinding
import com.example.home.di.HomeComponent
import com.example.home.di.HomeComponentViewModel
import com.example.home.di.Routes
import com.example.home.di.Screen

class HomeFragment: Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = _binding!!

    private var router: Routes? = null


    override fun onAttach(context: Context) {
        val componentViewModel = ViewModelProvider(this)[HomeComponentViewModel::class.java]
        componentViewModel.homeComponent.inject(this)
        super.onAttach(context)
        router = (context as? Routes)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            homeTextView.text = "Hello on Home fragment"
            btnGoToWeather.setOnClickListener {
                router?.navigateTo(Screen.WeatherScreen)
            }
            btnGoToNews.setOnClickListener {
                router?.navigateTo(Screen.NewsScreen)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        router = null
    }
    companion object{
        fun getInstance(): HomeFragment{
            return HomeFragment().apply {
                arguments = Bundle().apply {

                }
            }
        }
    }

}