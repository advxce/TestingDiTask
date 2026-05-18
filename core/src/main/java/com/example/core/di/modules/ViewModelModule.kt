package com.example.core.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core.di.factory.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.Multibinds
import javax.inject.Provider
import javax.inject.Singleton

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Multibinds
    fun viewModelMap(): Map<Class<out ViewModel>, @JvmSuppressWildcards ViewModel>

}