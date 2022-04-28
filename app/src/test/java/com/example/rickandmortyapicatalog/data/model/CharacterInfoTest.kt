package com.example.rickandmortyapicatalog.data.model

import com.example.rickandmortyapicatalog.helper.BaseUnitTest
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Test

class CharacterInfoTest : BaseUnitTest() {
    @Test
    fun constructor() = runBlocking {
        val model = CharacterInfo(
            "created", emptyList(), "gender",
            1, "image",
            Location("name", "url"), "name", Origin("name", "url"),
            "species", "status", "type", "url"
        )
        Truth.assertThat(model.created).isEqualTo("created")
        Truth.assertThat(model.episode).isEmpty()
        Truth.assertThat(model.gender).isEqualTo("gender")
        Truth.assertThat(model.id).isEqualTo("1")
        Truth.assertThat(model.image).isEqualTo("image")
        Truth.assertThat(model.location?.name).isEqualTo("name")
        Truth.assertThat(model.location?.url).isEqualTo("url")
        Truth.assertThat(model.name).isEqualTo("name")
        Truth.assertThat(model.origin?.name).isEqualTo("name")
        Truth.assertThat(model.origin?.url).isEqualTo("url")
        Truth.assertThat(model.species).isEqualTo("species")
        Truth.assertThat(model.status).isEqualTo("status")
        Truth.assertThat(model.type).isEqualTo("type")
        Truth.assertThat(model.url).isEqualTo("url")
    }
}
