package com.example.rickandmortyapicatalog.helper

import com.example.rickandmortyapicatalog.data.model.CharacterInfo
import com.example.rickandmortyapicatalog.data.model.Location
import com.example.rickandmortyapicatalog.data.model.Origin
import com.example.rickandmortyapicatalog.domain.uimodel.CharacterInfoUi
import com.example.rickandmortyapicatalog.domain.uimodel.LocationUi
import com.example.rickandmortyapicatalog.domain.uimodel.OriginUi

object CharacterListFactory {

    val characterList
        get() = listOf(
            characeterItem,
            characeterItem,
            characeterItem,
            characeterItem,
            characeterItem
        )

    val characeterItem
        get() = CharacterInfo(
            "created", emptyList(), "gender",
            1, "image",
            Location("name", "url"), "name", Origin("name", "url"),
            "species", "status", "type", "url"
        )

    val characterListUI
        get() = listOf(
            characeterItemUI,
            characeterItemUI,
            characeterItemUI,
            characeterItemUI,
            characeterItemUI
        )

    val characeterItemUI
        get() = CharacterInfoUi(
            "created", emptyList(), "gender",
            "1", "image",
            LocationUi("name", "url"), "name", OriginUi("name", "url"),
            "species", "status", "type", "url"
        )
}
