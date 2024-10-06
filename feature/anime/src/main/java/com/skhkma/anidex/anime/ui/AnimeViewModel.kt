package com.skhkma.anidex.anime.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.skhkma.anidex.network.datasource.AnimeRemoteDatasource
import com.skhkma.anidex.network.paging.AnimePagingSource

internal class AnimeViewModel(
    private val animeRemoteDatasource: AnimeRemoteDatasource
) : ViewModel() {

    val flow = Pager(
        PagingConfig(
            pageSize = 10,
        )
    ) {
        AnimePagingSource(remoteDataSource = animeRemoteDatasource)
    }.flow.cachedIn(viewModelScope)

}
