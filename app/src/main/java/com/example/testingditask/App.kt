package com.example.testingditask

import android.app.Application
import com.example.core.di.CoreComponent
import com.example.core.di.DaggerCoreComponent
import com.example.home.di.HomeDeps
import com.example.home.di.HomeDepsProvider
import com.example.testingditask.liveCodding.CarComponent
import com.example.testingditask.liveCodding.DaggerCarComponent

class App: Application(), HomeDepsProvider {

    lateinit var app: AppComponent

    lateinit var carComponent: CarComponent
    lateinit var coreComponent: CoreComponent

    override val homeDeps: HomeDeps by lazy { app }

    override fun onCreate() {
        super.onCreate()
        coreComponent = DaggerCoreComponent.builder().build()
        carComponent = DaggerCarComponent.create()
        app = DaggerAppComponent
            .builder()
            .coreComponent(coreComponent)
            .carComponent(carComponent)
            .build()



        HomeDepsProvider.deps = app
    }

}