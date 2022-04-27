package com.example.rickandmortyapicatalog.domain.mapper.abs

import androidx.paging.PagingData
import com.example.rickandmortyapicatalog.data.model.CharacterInfo
import com.example.rickandmortyapicatalog.domain.uimodel.CharacterInfoUi

interface CharacterListMapper {
    fun map(input: PagingData<CharacterInfo>): PagingData<CharacterInfoUi>
}