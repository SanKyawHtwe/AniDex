package com.skhkma.anidex.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skhkma.anidex.data.model.AnimeModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AnimeViewModel() : ViewModel() {

    private var _animeList: MutableStateFlow<List<AnimeModel>> = MutableStateFlow(listOf())
    var animeList: StateFlow<List<AnimeModel>> = _animeList

    init {
        fetchAnimeList()
    }

    fun fetchAnimeList() {
        _animeList.value = listOf(
            AnimeModel(
                id = 1,
                title = "One Piece",
                image = "https://gratisography.com/wp-content/uploads/2024/01/gratisography-cyber-kitty-800x525.jpg"
            ),
            AnimeModel(
                id = 2,
                title = "Naruto",
                image = "https://gratisography.com/wp-content/uploads/2024/01/gratisography-cyber-kitty-800x525.jpg"
            ),
            AnimeModel(
                id = 3,
                title = "Bleach",
                image = "https://gratisography.com/wp-content/uploads/2024/01/gratisography-cyber-kitty-800x525.jpg"
            ),
            AnimeModel(
                id = 4,
                title = "Dragon Ball",
                image = "https://gratisography.com/wp-content/uploads/2024/01/gratisography-cyber-kitty-800x525.jpg"
            ),
            AnimeModel(
                id = 5,
                title = "Dragon Ball",
                image = "https://gratisography.com/wp-content/uploads/2024/01/gratisography-cyber-kitty-800x525.jpg"
            ),
            AnimeModel(
                id = 6,
                title = "Dragon Ball",
                image = "https://gratisography.com/wp-content/uploads/2024/01/gratisography-cyber-kitty-800x525.jpg"
            ),
            AnimeModel(
                id = 7,
                title = "Dragon Ball",
                image = "https://gratisography.com/wp-content/uploads/2024/01/gratisography-cyber-kitty-800x525.jpg"
            )

        )
    }

}