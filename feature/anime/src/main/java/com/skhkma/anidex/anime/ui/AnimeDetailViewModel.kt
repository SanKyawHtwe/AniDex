package com.skhkma.anidex.anime.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skhkma.anidex.data.repository.AnimeRepository
import com.skhkma.anidex.model.AnimeDetailModel
import com.skhkma.anidex.model.CategoryModel
import com.skhkma.anidex.model.EpisodeModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

internal class AnimeDetailViewModel(
    private val animeId: String,
    private val animeRepository: AnimeRepository
) : ViewModel() {

    private var _detailUiState: MutableStateFlow<AnimeDetailUiState> = MutableStateFlow(
        AnimeDetailUiState.Loading
    )
    var detailUiState: StateFlow<AnimeDetailUiState> = _detailUiState

    private var _episodesUiState: MutableStateFlow<EpisodesUiState> = MutableStateFlow(
        EpisodesUiState.Loading
    )
    var episodesUiState: StateFlow<EpisodesUiState> = _episodesUiState

    private var _animeCategoryUiState: MutableStateFlow<AnimeCategoryUiState> = MutableStateFlow(
        AnimeCategoryUiState.Loading
    )
    var animeCategoryUiState: StateFlow<AnimeCategoryUiState> = _animeCategoryUiState

    private fun getAnimeDetail() {
        viewModelScope.launch {
            _detailUiState.value = AnimeDetailUiState.Loading
            animeRepository.getAnimeDetails(animeId).fold(
                {
                    _detailUiState.value = AnimeDetailUiState.Success(it)
//                    getAnimeEpisodes()
//                    getAnimeCategories()
                },
                {
                    _detailUiState.value =
                        AnimeDetailUiState.Error(it.message ?: "Something went wrong.")
                }
            )
        }
    }


    private fun getAnimeEpisodes() {
        viewModelScope.launch {
            _episodesUiState.value = EpisodesUiState.Loading
            animeRepository.getEpisodesByAnimeId(animeId).fold(
                {
                    _episodesUiState.value = EpisodesUiState.Success(it)
                },
                {
                    _episodesUiState.value =
                        EpisodesUiState.Error(it.message ?: "Something went wrong.")
                }
            )
        }
    }

    private fun getAnimeCategories() {
        viewModelScope.launch {
            _animeCategoryUiState.value = AnimeCategoryUiState.Loading
            animeRepository.getCategories(animeId).fold(
                {
                    _animeCategoryUiState.value = AnimeCategoryUiState.Success(categories = it)
                },
                {
                    _animeCategoryUiState.value =
                        AnimeCategoryUiState.Error(it.message ?: "Something went wrong.")
                }
            )
        }
    }

    init {
        getAnimeDetail()
    }

}

sealed class AnimeDetailUiState {
    data object Loading : AnimeDetailUiState()
    data class Success(val anime: AnimeDetailModel) : AnimeDetailUiState()
    data class Error(val message: String) : AnimeDetailUiState()
}

sealed class EpisodesUiState {
    data object Loading : EpisodesUiState()
    data class Success(val episodes: List<EpisodeModel>) : EpisodesUiState()
    data class Error(val message: String) : EpisodesUiState()
}

sealed class AnimeCategoryUiState {
    data object Loading : AnimeCategoryUiState()
    data class Success(val categories: List<CategoryModel>) : AnimeCategoryUiState()
    data class Error(val message: String) : AnimeCategoryUiState()
}