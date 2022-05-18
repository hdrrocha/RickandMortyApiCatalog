package com.example.rickandmortyapicatalog.domain.mapper

import androidx.paging.PagingData
import androidx.paging.map
import com.example.rickandmortyapicatalog.data.model.CharacterInfo
import com.example.rickandmortyapicatalog.data.model.Location
import com.example.rickandmortyapicatalog.data.model.Origin
import com.example.rickandmortyapicatalog.domain.mapper.abs.CharacterListMapper
import com.example.rickandmortyapicatalog.domain.uimodel.CharacterInfoUi
import com.example.rickandmortyapicatalog.domain.uimodel.LocationUi
import com.example.rickandmortyapicatalog.domain.uimodel.OriginUi

class CharacterListMapperImp : CharacterListMapper {
    override fun map(input: PagingData<CharacterInfo>) = input.map { chacter ->
        CharacterInfoUi(
            created = chacter.created,
            episode = chacter.episode,
            gender = chacter.gender,
            id = chacter.id.toString(),
            image = chacter.image,
            location = mapLocationUi(chacter.location),
            name = chacter.name,
            origin = mapOriginUi(chacter.origin),
            species = chacter.species,
            status = chacter.status,
            type = chacter.type,
            url = chacter.url,
        )
    }

    private fun mapLocationUi(location: Location?): LocationUi? {
        return LocationUi(
            name = location?.name.orEmpty(),
            url = location?.url.orEmpty()
        )
    }

    private fun mapOriginUi(origin: Origin?): OriginUi? {
        return OriginUi(
            name = origin?.name.orEmpty(),
            url = origin?.url.orEmpty()
        )
    }
}