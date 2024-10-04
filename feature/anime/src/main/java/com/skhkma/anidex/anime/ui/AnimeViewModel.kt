package com.skhkma.anidex.anime.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.skhkma.anidex.model.AnimeModel
import com.skhkma.anidex.network.paging.AnimePagingSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal class AnimeViewModel(
    private val animePagingSource: AnimePagingSource,
) : ViewModel() {

//    private var _uiState: MutableStateFlow<TrendingAnimeUiState> = MutableStateFlow(
//        TrendingAnimeUiState.Loading
//    )
//    var uiState: StateFlow<TrendingAnimeUiState> = _uiState

    val flow = Pager(
        PagingConfig(
            pageSize = 10,
        )
    ) {
        animePagingSource
    }.flow.cachedIn(viewModelScope)


//    init {
//        fetchAnimeList()
//    }

//    fun fetchAnimeList() {
//        viewModelScope.launch {
//            _uiState.value = TrendingAnimeUiState.Loading
//            animeRepository.getAnimeList().fold(
//                onSuccess = {
//                    _uiState.value = TrendingAnimeUiState.Success(animeList = it)
//                },
//                onFailure = {
//                    _uiState.value = TrendingAnimeUiState.Error("Something went wrong")
//                }
//            )
//        }
//    }

}


//sealed class TrendingAnimeUiState {
//    data object Loading : TrendingAnimeUiState()
//    data class Success(var animeList: List<AnimeModel>) : TrendingAnimeUiState()
//    data class Error(var message: String) : TrendingAnimeUiState()
//}