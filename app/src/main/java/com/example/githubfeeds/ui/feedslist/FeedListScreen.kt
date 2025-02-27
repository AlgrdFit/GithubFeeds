package com.example.githubfeeds.ui.feedslist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController

@Composable
fun FeedListScreen(navController: NavController) {
    val viewModel: FeedListViewModel = hiltViewModel()
    val state = viewModel.state.collectAsStateWithLifecycle()

    val modifier = Modifier

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(onClick = {
/*            navController.navigate(
                AddAllergensScreen(
                    userName = "Algirdas",
                    allergenEntity = AllergenEntity(
                        id = "id",
                        name = "allergen name"
                    )
                )
            )*/
        }) {
            Text(text = "go to Allergen selection")
        }
    }
}