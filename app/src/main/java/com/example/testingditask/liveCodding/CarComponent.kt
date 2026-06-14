package com.example.testingditask.liveCodding

import com.example.testingditask.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CarModule::class])
interface CarComponent {


    @Car1Qualifier
    fun getCar1(): Car

    @Car2Qualifier
    fun getCar2(): Car

    @Car3Qualifier
    fun getCar3(): Car

//    @Component.Builder
//    interface Builder {
//        fun build(): CarComponent
//    }

}