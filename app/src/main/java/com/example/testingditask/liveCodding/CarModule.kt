package com.example.testingditask.liveCodding

import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Singleton

//@Module
//interface CarModule {
//
//    @Binds
//    @Car1Qualifier
//    fun bindCar1(car: Car1): Car
//
//    @Binds
//    @Car2Qualifier
//    fun bindCar2(car2: Car2): Car
//
//    @Binds
//    @Car3Qualifier
//    fun bindCar3(car3: Car3): Car
//
//}

@Module
interface CarModule {

    @Binds
    @Singleton
    @Car1Qualifier
    fun bindCar1(car: Car1): Car

    @Binds
    @Singleton
    @Car2Qualifier
    fun bindCar2(car2: Car2): Car

    @Binds
    @Singleton
    @Car3Qualifier
    fun bindCar3(car3: Car3): Car

}



