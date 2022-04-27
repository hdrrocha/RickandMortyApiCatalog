package com.example.rickandmortyapicatalog.domain.repository

import androidx.paging.PagingData
import com.example.rickandmortyapicatalog.data.model.CharacterInfo
import kotlinx.coroutines.flow.Flow

interface CharacterListRepository {
    fun fetchCharacter(): Flow<PagingData<CharacterInfo>>
}