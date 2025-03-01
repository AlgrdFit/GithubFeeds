package com.example.githubfeeds.ui.detailedfeeds

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubfeeds.network.api.GithubFeedsApi
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = DetailedFeedViewModel.DetailedFeedViewModelFactory::class)
class DetailedFeedViewModel @AssistedInject constructor(
    private val feedsApi: GithubFeedsApi,
    @Assisted private val args: Arguments,
) : ViewModel() {

    private val _state = MutableStateFlow(DetailedFeedState())
    val state = _state.asStateFlow()
    
    init {
        viewModelScope.launch { 
            try {
                val feedItem = feedsApi.getExplicitUrl(url = args.url).body()
                _state.update {
                    it.copy(
                        feed = feedItem?.string()
                    )
                }
            } catch (e: Exception) {
                Log.e("DetailedFeedViewModel", e.message ?: e.toString())
            }
        }
    }

    data class Arguments(
        val url: String,
    )

    @AssistedFactory
    interface DetailedFeedViewModelFactory {
        fun create(@Assisted args: Arguments): DetailedFeedViewModel
    }
}