package com.example.githubfeeds.network.api

import com.example.githubfeeds.data.models.response.GithubFeedsResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface GithubFeedsApi {

    @GET("/feeds")
    suspend fun getGitFeeds(): Response<GithubFeedsResponse>

    @GET
    suspend fun getExplicitUrl(@Url url: String): Response<ResponseBody>
}