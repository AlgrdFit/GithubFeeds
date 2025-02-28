package com.example.githubfeeds.ui.feedslist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.githubfeeds.data.models.entities.FeedPlaceholder

@Composable
fun FeedListScreen(navController: NavController) {
    val viewModel: FeedListViewModel = hiltViewModel()
    val state = viewModel.state.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(state.value.gitFeedsList) { feedEntity ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                val userInputs = remember { mutableStateMapOf<FeedPlaceholder, String>() }

                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Feed Template:",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = feedEntity.feedTemplate,
                        style = MaterialTheme.typography.bodyMedium
                    )

                    feedEntity.feedFields.forEach { (placeholder, _) ->
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = userInputs[placeholder] ?: "",
                            onValueChange = { newValue ->
                                userInputs[placeholder] = newValue
                            },
                            label = { Text("Enter ${placeholder.placeholder}") },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Button(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        onClick = {
                            viewModel.updateFinalUrlForFeed(feedEntity.feedTemplate, userInputs)
                        },
                    ) {
                        Text("Construct URL")
                    }

                    //showing final feed url
                    feedEntity.finalUrl?.let { finalUrl ->
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Final URL: $finalUrl",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}