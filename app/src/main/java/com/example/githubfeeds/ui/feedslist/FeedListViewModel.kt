package com.example.githubfeeds.ui.feedslist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubfeeds.data.models.entities.FeedEntity
import com.example.githubfeeds.data.models.entities.FeedPlaceholder
import com.example.githubfeeds.data.models.response.GithubFeedsResponse
import com.example.githubfeeds.network.api.GithubFeedsApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.reflect.full.memberProperties

@HiltViewModel
class FeedListViewModel @Inject constructor(
    private val feedsApi: GithubFeedsApi
) : ViewModel() {

    private val _state = MutableStateFlow(FeedListState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val gitFeedsList = feedsApi.getGitFeeds().body()?.toFeedsList()
                _state.update {
                    it.copy(
                        gitFeedsList = gitFeedsList ?: emptyList()
                    )
                }
                Log.d("response body", gitFeedsList.toString())
            } catch (e: Exception) {
                Log.e("response error", e.message ?: e.toString())
            }
        }
    }

    private fun GithubFeedsResponse.toFeedsList(): List<FeedEntity> {
        return GithubFeedsResponse::class.memberProperties.mapNotNull { property ->
            val feed = when (val propertyValue = property.get(this)) {
                is String -> propertyValue.ifBlank { null }
                is List<*> -> {
                    if (propertyValue.isNotEmpty()) {
                        propertyValue.joinToString(", ")
                    } else {
                        null
                    }
                }

                else -> null
            }

            feed?.let {
                FeedEntity(
                    feedTemplate = it,
                    feedFields = FeedPlaceholder.entries.mapNotNull { entry ->
                        if (it.contains(entry.placeholder)) {
                            entry to null
                        } else {
                            null
                        }
                    }.toMap(),
                )
            }
        }
    }

    fun onConstructUrlClick(feedTemplate: String, userInputs: Map<FeedPlaceholder, String>) {
        var finalUrl = feedTemplate
        userInputs.forEach { (key, value) ->
            finalUrl = finalUrl.replace("{${key.placeholder}}", value.trim())
        }

        _state.update { state ->
            val updatedList = state.gitFeedsList.map { item ->
                if (item.feedTemplate == feedTemplate) {
                    item.copy(finalUrl = finalUrl)
                } else {
                    item
                }
            }
            state.copy(gitFeedsList = updatedList)
        }
    }
}