package com.example.githubfeeds.network.api

import com.example.githubfeeds.data.models.GithubFeedsResponse
import retrofit2.Response
import retrofit2.http.GET

interface GithubFeedsApi {

    @GET("/feeds")
    suspend fun getGitFeeds(): Response<GithubFeedsResponse>
}