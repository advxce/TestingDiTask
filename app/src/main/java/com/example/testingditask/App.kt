package com.example.testingditask

import android.app.Application
import com.example.core.di.CoreComponent
import com.example.core.di.DaggerCoreComponent
import com.example.home.di.HomeDeps
import com.example.home.di.HomeDepsProvider

class App: Application(), HomeDepsProvider {

    lateinit var app: AppComponent
    lateinit var coreComponent: CoreComponent

    override val homeDeps: HomeDeps by lazy { app }

    override fun onCreate() {
        super.onCreate()
        coreComponent = DaggerCoreComponent.builder().build()
        app = DaggerAppComponent
            .builder()
            .coreComponent(coreComponent)
            .build()
        HomeDepsProvider.deps = app
    }

}