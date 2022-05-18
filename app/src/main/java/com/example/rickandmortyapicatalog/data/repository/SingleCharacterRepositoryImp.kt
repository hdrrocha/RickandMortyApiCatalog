package com.example.rickandmortyapicatalog.data.repository

import com.example.rickandmortyapicatalog.data.api.RickAndMortyApi
import com.example.rickandmortyapicatalog.data.model.CharacterInfo
import com.example.rickandmortyapicatalog.domain.repository.SingleCharacterRepository

class SingleCharacterRepositoryImp(
    private val rickAndMortyApi: RickAndMortyApi
) : SingleCharacterRepository {
    override suspend fun getSingleCharacter(id: String): CharacterInfo {
        return rickAndMortyApi.getSingleCharacter(id)
    }
}
