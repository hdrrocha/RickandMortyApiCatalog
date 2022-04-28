package com.example.rickandmortyapicatalog.data.model

import com.example.rickandmortyapicatalog.helper.BaseUnitTest
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Test

class CharacterListTest : BaseUnitTest() {
    @Test
    fun constructor() = runBlocking {
        val model = CharacterList(Info(1, "2", 10, "prev"), emptyList())

        Truth.assertThat(model.info.count).isEqualTo(1)
        Truth.assertThat(model.info.next).isEqualTo("2")
        Truth.assertThat(model.info.pages).isEqualTo(10)
        Truth.assertThat(model.info.prev).isEqualTo("prev")
        Truth.assertThat(model.results).isEmpty()

    }
}