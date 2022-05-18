package com.example.rickandmortyapicatalog.domain.usercase

import com.example.rickandmortyapicatalog.domain.mapper.abs.CharacterListMapper
import com.example.rickandmortyapicatalog.domain.repository.CharacterListRepository
import com.example.rickandmortyapicatalog.domain.usercase.abs.CharacterListUseCase
import kotlinx.coroutines.flow.map

class CharacterListUseCaseImp(
    private val characterListRepository: CharacterListRepository,
    private val mapper: CharacterListMapper
) : CharacterListUseCase {
    override fun fetchCharacter() = characterListRepository.fetchCharacter().map { mapper.map(it) }
}