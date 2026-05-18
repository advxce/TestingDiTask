package com.example.home.di

import kotlin.properties.Delegates


interface HomeDepsProvider {
    val homeDeps: HomeDeps

    companion object {
        var deps: HomeDeps by Delegates.notNull()
    }
}
