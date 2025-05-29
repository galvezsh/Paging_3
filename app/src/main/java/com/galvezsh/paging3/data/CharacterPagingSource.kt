package com.galvezsh.paging3.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.galvezsh.paging3.data.response.CharacterResponse
import com.galvezsh.paging3.presentation.model.CharacterModel
import okio.IOException

class CharacterPagingSource(
    private val api: RickAndMortyApiService,
    private val name: String?
): PagingSource<Int, CharacterModel>() {

    override fun getRefreshKey( state: PagingState<Int, CharacterModel> ): Int? {
        return state.anchorPosition
    }

    override suspend fun load( params: LoadParams<Int> ): LoadResult<Int, CharacterModel> {
        return try {
            val page = params.key ?: 1
            val response = api.getCharacters( page, name )

            if ( response.isSuccessful ) {
                val body = response.body()
                val characters = body?.results ?: emptyList<CharacterResponse>()

                val prevKey = if ( body?.info?.prev != null ) page-1 else null
                val nextKey = if ( body?.info?.next != null ) page+1 else null

                LoadResult.Page(
                    data = characters.map { character -> character.toMap() },
                    prevKey = prevKey,
                    nextKey = nextKey
                )

            // No data
            } else {
                LoadResult.Page(
                    data = emptyList<CharacterModel>(),
                    prevKey = null,
                    nextKey = null
                )
            }

        // No internet catch
        } catch ( exception: IOException ) {
            LoadResult.Error( exception )
        }
    }
}