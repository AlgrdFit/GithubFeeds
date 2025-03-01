package com.example.githubfeeds.network.interceptors

import android.util.Base64
import com.example.githubfeeds.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val username = BuildConfig.GITHUB_USERNAME
        val token = BuildConfig.GITHUB_TOKEN
        val credentials = "$username:$token"
        val basicAuth = "Basic " + Base64.encodeToString(credentials.toByteArray(), Base64.NO_WRAP)
        val request = chain.request().newBuilder()
            .addHeader("Authorization", basicAuth)
            .build()
        return chain.proceed(request)
    }
}