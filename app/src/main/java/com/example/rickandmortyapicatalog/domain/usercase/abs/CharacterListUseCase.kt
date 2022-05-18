package com.example.rickandmortyapicatalog.domain.usercase.abs

import androidx.paging.PagingData
import com.example.rickandmortyapicatalog.domain.uimodel.CharacterInfoUi
import kotlinx.coroutines.flow.Flow

interface CharacterListUseCase {
    fun fetchCharacter(): Flow<PagingData<CharacterInfoUi>>
}