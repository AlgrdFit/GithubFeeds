package com.example.githubfeeds.ui.detailedfeeds

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.githubfeeds.ui.detailedfeeds.DetailedFeedViewModel.Arguments
import com.example.githubfeeds.ui.detailedfeeds.DetailedFeedViewModel.DetailedFeedViewModelFactory
import com.example.githubfeeds.utils.Router

@Composable
fun DetailedFeedScreen(
    args: Router.DetailedFeedScreen,
) {
    val viewModel: DetailedFeedViewModel = hiltViewModel { factory: DetailedFeedViewModelFactory ->
        factory.create(Arguments(args.url))
    }
    val state = viewModel.state.collectAsStateWithLifecycle()

    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        Text(
            text = state.value.feed ?: "No content",
        )
    }
}