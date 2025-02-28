package com.example.githubfeeds.ui.feedslist

import com.example.githubfeeds.data.models.entities.FeedEntity

data class FeedListState(
    val gitFeedsList: List<FeedEntity> = emptyList()
)
