package com.example.rickandmortyapicatalog.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmortyapicatalog.data.api.RickAndMortyApi
import com.example.rickandmortyapicatalog.data.model.CharacterInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterListPagingSource(val api: RickAndMortyApi) : PagingSource<Int, CharacterInfo>() {

    companion object {
        const val INITIAL_START = 1
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterInfo>) = state.anchorPosition?.let {
        state.closestPageToPosition(it)?.prevKey
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterInfo> {
        val start = params.key?.let { it + INITIAL_START } ?: INITIAL_START
        val limit = params.key?.let { it + params.loadSize } ?: params.loadSize

        val data = withContext(Dispatchers.IO) { api.fetchCharacter(start, limit) }

        return LoadResult.Page(
            data.results.orEmpty(),
            prevKey = if (start == INITIAL_START) null else start,
            nextKey = limit
        )
    }
}
