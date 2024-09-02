package com.skhkma.anidex.features.home.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skhkma.anidex.features.home.data.repository.AnimeRepository
import com.skhkma.anidex.features.home.domain.model.AnimeModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AnimeViewModel(
    private val animeRepository: AnimeRepository
) : ViewModel() {

    private var _uiState: MutableStateFlow<TrendingAnimeUiState> = MutableStateFlow(TrendingAnimeUiState.Loading)
    var uiState : StateFlow<TrendingAnimeUiState> = _uiState


    init {
        fetchAnimeList()
    }

    fun fetchAnimeList() {
        viewModelScope.launch {
            _uiState.value = TrendingAnimeUiState.Loading
            animeRepository.getAnimeList().fold(
                onSuccess = {
                    _uiState.value = TrendingAnimeUiState.Success(animeList = it)
                },
                onFailure = {
                    _uiState.value = TrendingAnimeUiState.Error("Something went wrong")
                }
            )
        }
    }

}


sealed class TrendingAnimeUiState {
    data object Loading : TrendingAnimeUiState()
    data class Success(var animeList: List<AnimeModel>) : TrendingAnimeUiState()
    data class Error (var message : String) : TrendingAnimeUiState()
}