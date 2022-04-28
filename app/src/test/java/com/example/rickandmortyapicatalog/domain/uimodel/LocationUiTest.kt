package com.example.rickandmortyapicatalog.domain.uimodel

import com.example.rickandmortyapicatalog.helper.BaseUnitTest
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Test

class LocationUiTest : BaseUnitTest() {
    @Test
    fun constructor() = runBlocking {
        val model = LocationUi("name", "url")

        Truth.assertThat(model.name).isEqualTo("name")
        Truth.assertThat(model.url).isEqualTo("url")
    }
}