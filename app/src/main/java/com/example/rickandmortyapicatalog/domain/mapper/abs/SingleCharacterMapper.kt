package com.example.rickandmortyapicatalog.domain.mapper.abs

import com.example.rickandmortyapicatalog.data.model.CharacterInfo
import com.example.rickandmortyapicatalog.domain.uimodel.CharacterInfoUi


interface SingleCharacterMapper {
    fun map(input: CharacterInfo): CharacterInfoUi
}