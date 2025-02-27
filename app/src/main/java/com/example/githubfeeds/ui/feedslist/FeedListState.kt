package com.example.githubfeeds.ui.feedslist

import com.example.githubfeeds.data.models.GithubFeedsResponse

data class FeedListState(
    val isLoading: Boolean = false,
    val feeds: GithubFeedsResponse? = null,
    val error: String = ""

)
