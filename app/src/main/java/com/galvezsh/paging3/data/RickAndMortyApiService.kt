package com.galvezsh.paging3.data

import com.galvezsh.paging3.data.response.CharacterResponse
import com.galvezsh.paging3.data.response.ResponseWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApiService {
    // Like this 'api/character/?page=int' o 'api/character/?page=int&name=string'
    @GET("/api/character/")
    suspend fun getCharacters( @Query("page") page: Int, @Query("name") name: String? ): Response<ResponseWrapper>

    @GET("/api/character/{id}")
    suspend fun getCharacterById( @Path("id") id: Int ): Response<CharacterResponse>
}