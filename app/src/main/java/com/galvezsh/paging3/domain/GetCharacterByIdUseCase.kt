package com.galvezsh.paging3.domain

import com.galvezsh.paging3.data.RickAndMortyRepository
import com.galvezsh.paging3.data.response.CharacterResponse
import javax.inject.Inject

class GetCharacterByIdUseCase @Inject constructor( private val rickAndMortyRepository: RickAndMortyRepository ) {

    suspend operator fun invoke(id: Int ): CharacterResponse? {
        return rickAndMortyRepository.getCharacterById( id )
    }
}