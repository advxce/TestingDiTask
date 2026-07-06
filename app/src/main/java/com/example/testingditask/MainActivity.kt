package com.example.testingditask

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.core.domain.usecases.GetAllNewsUseCase
import com.example.home.di.Routes
import com.example.home.di.Screen
import com.example.home.presentation.Home.HomeFragment
import com.example.home.presentation.News.NewsFragment
import com.example.home.presentation.Weather.WeatherFragment
import com.example.testingditask.liveCodding.Car
import com.example.testingditask.liveCodding.Car1
import com.example.testingditask.liveCodding.Car1Qualifier
import com.example.testingditask.liveCodding.Car2
import com.example.testingditask.liveCodding.Car2Qualifier
import com.example.testingditask.liveCodding.Car3
import com.example.testingditask.liveCodding.Car3Qualifier
import com.example.testingditask.liveCodding.CarComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity(), Routes {
    private val mainViewModel: MainViewModel by viewModels()
    lateinit var appComponent: AppComponent
//    lateinit var carComponent: CarComponent
    @Inject
    @Car1Qualifier
    lateinit var car1: Car

    @Inject
    @Car2Qualifier
    lateinit var car2: Car
    @Inject
    @Car3Qualifier
    lateinit var car3: Car

//    @Inject
//    lateinit var analyticsTracker: AnalyticsTracker
//
//    @Inject
//    lateinit var orderManager: OrderManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        carComponent = (application as App).carComponent
//        carComponent.inject(this)

        appComponent = (application as App).app
        appComponent.inject(this)

        car1.drive()
        car2.drive()
        car3.drive()
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initObserver()



//        analyticsTracker.initLogger()
//        repeat(3){
//            orderManager.createOrder()
//        }
    }

    private fun initObserver() {
        lifecycleScope.launch {
            mainViewModel.currentFragment.collect { screen ->
                when(screen){
                    Screen.HomeScreen -> {replaceFragment(HomeFragment())}
                    Screen.WeatherScreen -> {replaceFragment(WeatherFragment())}
                    Screen.NewsScreen -> {replaceFragment(NewsFragment())}
                }
            }
        }

    }


    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun navigateTo(screen: Screen) {
        mainViewModel.setFragment(screen)
    }
}
