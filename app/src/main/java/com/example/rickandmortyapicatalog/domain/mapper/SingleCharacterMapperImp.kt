package com.example.rickandmortyapicatalog.domain.mapper

import androidx.paging.PagingData
import androidx.paging.map
import com.example.rickandmortyapicatalog.data.model.CharacterInfo
import com.example.rickandmortyapicatalog.data.model.Location
import com.example.rickandmortyapicatalog.data.model.Origin
import com.example.rickandmortyapicatalog.domain.mapper.abs.CharacterListMapper
import com.example.rickandmortyapicatalog.domain.mapper.abs.SingleCharacterMapper
import com.example.rickandmortyapicatalog.domain.uimodel.CharacterInfoUi
import com.example.rickandmortyapicatalog.domain.uimodel.LocationUi
import com.example.rickandmortyapicatalog.domain.uimodel.OriginUi
import com.example.rickandmortyapicatalog.ui.utils.extractYear

class SingleCharacterMapperImp : SingleCharacterMapper {
    override fun map(input: CharacterInfo): CharacterInfoUi {
        return  CharacterInfoUi(
            created = input.created?.extractYear().orEmpty(),
            episode = input.episode,
            gender = input.gender,
            id = input.id.toString(),
            image = input.image,
            location = mapLocationUi(input.location),
            name = input.name,
            origin = mapOriginUi(input.origin),
            species = input.species,
            status = input.status,
            type = input.type,
            url = input.url,
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