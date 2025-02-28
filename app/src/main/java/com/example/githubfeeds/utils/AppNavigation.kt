package com.example.githubfeeds.utils

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.githubfeeds.data.models.response.GithubFeedsResponse
import com.example.githubfeeds.ui.detailedfeeds.DetailedFeedScreen
import com.example.githubfeeds.ui.feedslist.FeedListScreen
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Router.FeedListScreen) {
        composable<Router.FeedListScreen> {
            FeedListScreen(navController)
        }
        composable<Router.DetailedFeedScreen>(
            typeMap = mapOf(
                typeOf<GithubFeedsResponse>() to GenericNavType(
                    serializer = GithubFeedsResponse.serializer(),
                    isNullableAllowed = false
                )
            )
        ) {
            val args = it.toRoute<Router.DetailedFeedScreen>()
            DetailedFeedScreen(navController, args)
        }
    }

}

sealed class Router {
    @Serializable
    data object FeedListScreen : Router()

    @Serializable
    data class DetailedFeedScreen(
        val args: GithubFeedsResponse
    ) : Router()
}