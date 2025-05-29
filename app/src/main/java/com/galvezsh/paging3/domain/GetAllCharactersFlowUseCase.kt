package com.galvezsh.paging3.domain

import androidx.paging.PagingData
import com.galvezsh.paging3.data.RickAndMortyRepository
import com.galvezsh.paging3.presentation.model.CharacterModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCharactersFlowUseCase @Inject constructor( val rickAndMortyRepository: RickAndMortyRepository ) {

    operator fun invoke( name: String? ): Flow<PagingData< CharacterModel >> = rickAndMortyRepository.getAllCharacters( name )
}