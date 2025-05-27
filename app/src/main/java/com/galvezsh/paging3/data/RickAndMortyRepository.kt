package com.galvezsh.paging3.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.galvezsh.paging3.data.response.CharacterResponse
import com.galvezsh.paging3.presentation.model.CharacterModel
import kotlinx.coroutines.flow.Flow
import okio.IOException
import javax.inject.Inject

class RickAndMortyRepository @Inject constructor( private val api: RickAndMortyApiService ) {

    companion object {
        const val MAX_ITEMS = 10
        const val PREFETCH_ITEMS = 5
    }

    fun getAllCharacters(): Flow<PagingData<CharacterModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = MAX_ITEMS,
                prefetchDistance = PREFETCH_ITEMS
            ),
            pagingSourceFactory = { CharacterPagingSource( api ) }
        ).flow
    }

    suspend fun getCharacterById( id: Int ): CharacterResponse? {
        return try {
            api.getCharacterById( id )

        } catch (e: Exception) {
            null
        }
    }
}