package com.example.rickandmortyapicatalog.aplication

import com.example.rickandmortyapicatalog.data.model.CharacterInfo
import com.example.rickandmortyapicatalog.data.model.CharacterList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApi {
    @GET("character/")
    suspend fun fetchCharacter(
        @Query("limit") limit: Int = 100,
        @Query("offset") offset: Int = 200
    ): CharacterList

    @GET("pokemon/{id}/")
    suspend fun getSingleCharacter(
        @Path("id") name: String
    ): CharacterInfo
}