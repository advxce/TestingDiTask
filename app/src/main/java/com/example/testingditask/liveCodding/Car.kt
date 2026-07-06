package com.example.testingditask.liveCodding

import javax.inject.Inject

interface Car {
    fun drive()
}


class Car1 @Inject constructor() : Car {
    override fun drive() {
        println("Car1 is driving")
    }
}

class Car2 @Inject constructor(): Car{
    override fun drive() {
        println("Car2 is driving")
    }
}

class Car3 @Inject constructor() : Car{
    override fun drive() {
        println("Car3 is driving")
    }
}




