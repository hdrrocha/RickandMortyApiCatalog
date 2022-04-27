package com.example.rickandmortyapicatalog.data.repository

import androidx.paging.Pager
import com.example.rickandmortyapicatalog.data.model.CharacterInfo
import com.example.rickandmortyapicatalog.domain.repository.CharacterListRepository

class CharacterListRepositoryImp (
    private val pager: Pager<Int, CharacterInfo>
) : CharacterListRepository {
    companion object {
        const val DEFAULT_LIST_SIZE = 15
    }

    override fun fetchCharacter() = pager.flow
}
