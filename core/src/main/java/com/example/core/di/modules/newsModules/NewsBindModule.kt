package com.example.core.di.modules.newsModules

import com.example.core.data.newsApi.repository.NewsRepositoryImpl
import com.example.core.domain.repositories.NewsRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface NewsBindModule {

    @Binds
    @Singleton
    fun bindNewsRepository(impl: NewsRepositoryImpl): NewsRepository

}