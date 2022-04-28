package com.example.rickandmortyapicatalog.domain.repository

import com.example.rickandmortyapicatalog.data.model.CharacterInfo

interface SingleCharacterRepository {
    suspend fun getSingleCharacter(id: String): CharacterInfo
}