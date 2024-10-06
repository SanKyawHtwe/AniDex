package com.skhkma.anidex.network.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.skhkma.anidex.model.AnimeModel
import com.skhkma.anidex.network.datasource.AnimeRemoteDatasource

class AnimePagingSource(
    private val remoteDataSource: AnimeRemoteDatasource
): PagingSource<Int, AnimeModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AnimeModel> {
        try {
            val nextOffset = params.key ?: 0
            val response = remoteDataSource.getAnimeList(offset = nextOffset)
            return LoadResult.Page(
                data = response,
                prevKey = null, // Only paging forward.
                nextKey = if (params.key == null) 10 else params.loadSize + nextOffset
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, AnimeModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            // Assuming each page has a fixed size
            val pageSize = state.config.pageSize

            // Calculate the offset based on the anchor position
            anchorPosition.div(pageSize) * pageSize
        }
    }

}