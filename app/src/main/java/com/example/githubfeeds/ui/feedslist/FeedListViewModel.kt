package com.example.githubfeeds.ui.feedslist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubfeeds.network.api.GithubFeedsApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedListViewModel @Inject constructor(
    private val feedsApi: GithubFeedsApi
) : ViewModel()  {

    private val _state = MutableStateFlow(FeedListState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                val response = feedsApi.getGitFeeds()
/*                _state.update {
                    it.copy(feeds = response.body())
                }*/
                Log.d("response body", response.body().toString())
            } catch (e: Exception) {
                Log.e("response error", e.message ?: e.toString())
/*                _state.update {
                    it.copy(error = e.message ?: "Unknown error")
                }*/
            }
        }
    }
}