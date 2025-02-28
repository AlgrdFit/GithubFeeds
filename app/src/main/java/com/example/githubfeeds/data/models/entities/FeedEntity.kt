package com.example.githubfeeds.data.models.entities

data class FeedEntity(
    val feedTemplate: String,
    val feedFields: Map<FeedPlaceholder, String?> = emptyMap(),
    val finalUrl: String? = null,
)

enum class FeedPlaceholder(val placeholder: String) {
    USER("user"),
    REPO("repo"),
    CATEGORY("category"),
    UNKNOWN("unknown");
}
