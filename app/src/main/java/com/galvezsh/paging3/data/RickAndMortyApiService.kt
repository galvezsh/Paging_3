package com.galvezsh.paging3.data

import com.galvezsh.paging3.data.response.ResponseWrapper
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApiService {
    // Like this 'api/character/?page=int'
    @GET("/api/character/")
    suspend fun getCharacters( @Query("page") page: Int ): ResponseWrapper
}