package com.example.githubfeeds.data.models

import kotlinx.serialization.Serializable

@Serializable
data class GithubFeedsResponse(
    val timelineUrl: String,
    val userUrl: String,
    val currentUserPublicUrl: String?,
    val currentUserUrl: String?,
    val currentUserActorUrl: String?,
    val currentUserOrganizationUrl: String?,
    val currentUserOrganizationUrls: List<String>?,
    val securityAdvisoriesUrl: String?,
    val repositoryDiscussionsUrl: String?,
    val repositoryDiscussionsCategoryUrl: String?,
    val links: FeedLinks
)

@Serializable
data class FeedLinks(
    val timeline: LinkWithType,
    val user: LinkWithType,
    val securityAdvisories: LinkWithType?,
    val currentUser: LinkWithType?,
    val currentUserPublic: LinkWithType?,
    val currentUserActor: LinkWithType?,
    val currentUserOrganization: LinkWithType?,
    val currentUserOrganizations: List<LinkWithType>?,
    val repositoryDiscussions: LinkWithType?,
    val repositoryDiscussionsCategory: LinkWithType?
)

@Serializable
data class LinkWithType(
    val href: String,
    val type: String
)