package com.example.githubfeeds.data.models.response

import kotlinx.serialization.Serializable

@Serializable
data class GithubFeedsResponse(
    val timelineUrl: String?,
    val userUrl: String?,
    val currentUserPublicUrl: String?,
    val currentUserUrl: String?,
    val currentUserActorUrl: String?,
    val currentUserOrganizationUrl: String?,
    val securityAdvisoriesUrl: String?,
    val repositoryDiscussionsUrl: String?,
    val repositoryDiscussionsCategoryUrl: String?,
)