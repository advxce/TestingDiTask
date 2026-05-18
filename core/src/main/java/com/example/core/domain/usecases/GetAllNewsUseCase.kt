package com.example.core.domain.usecases

import com.example.core.domain.models.news.News
import com.example.core.domain.repositories.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAllNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(): Result<News> = withContext(Dispatchers.IO){
        return@withContext newsRepository.getAllNews()
    }
}