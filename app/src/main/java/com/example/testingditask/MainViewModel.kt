package com.example.testingditask

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.home.di.Routes
import com.example.home.di.Screen
import com.example.home.presentation.Home.HomeFragment
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
    private var _currentFragment = MutableStateFlow<Screen>(Screen.HomeScreen)
    val currentFragment
        get() = _currentFragment.asStateFlow()


    fun setFragment(screen: Screen) {
        println("screenTo :$screen")
        _currentFragment.value = screen
    }

}

