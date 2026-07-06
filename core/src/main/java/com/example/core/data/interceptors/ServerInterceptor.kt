package com.example.core.data.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ServerInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        getCode(response)
        getResponseBody(response)
        return response
    }

    private fun getCode(response: Response): Int {
        val code = response.code
        println("code: $code")
        return code
    }
//
    private fun getResponseBody(response: Response) {
        val responseBody = response.body
        if (responseBody != null) {
            val source = responseBody.source()
            source.request(Long.MAX_VALUE)
            val buffer = source.buffer.clone()

            val bodyString = buffer.readString(Charsets.UTF_8)

            println("response body: $bodyString")
        } else {
            println("response body is null")
        }
    }
}