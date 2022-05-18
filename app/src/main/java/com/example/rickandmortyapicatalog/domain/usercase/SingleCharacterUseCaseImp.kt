package com.example.rickandmortyapicatalog.domain.usercase

import com.example.rickandmortyapicatalog.domain.mapper.abs.SingleCharacterMapper
import com.example.rickandmortyapicatalog.domain.repository.SingleCharacterRepository
import com.example.rickandmortyapicatalog.domain.uimodel.CharacterInfoUi
import com.example.rickandmortyapicatalog.domain.usercase.abs.SingleCharactertUseCase

class SingleCharacterUseCaseImp(
    private val singleCharacterRepository: SingleCharacterRepository,
    private val mapper: SingleCharacterMapper
) : SingleCharactertUseCase {
    override suspend fun getSingleCharacter(id: String): CharacterInfoUi {
        return mapper.map(singleCharacterRepository.getSingleCharacter(id))
    }


}