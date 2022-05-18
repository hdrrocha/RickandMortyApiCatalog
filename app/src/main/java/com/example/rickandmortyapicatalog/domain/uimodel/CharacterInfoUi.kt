package com.example.rickandmortyapicatalog.domain.uimodel


data class CharacterInfoUi(
    var created: String?,
    var episode: List<String>?,
    var gender: String?,
    var id: String?,
    var image: String?,
    var location: LocationUi?,
    var name: String?,
    var origin: OriginUi?,
    var species: String?,
    var status: String?,
    var type: String?,
    var url: String?
)