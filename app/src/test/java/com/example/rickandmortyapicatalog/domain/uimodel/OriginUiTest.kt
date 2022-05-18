package com.example.rickandmortyapicatalog.domain.uimodel

import com.example.rickandmortyapicatalog.helper.BaseUnitTest
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Test

class OriginUiTest : BaseUnitTest() {
    @Test
    fun constructor() = runBlocking {
        val model = OriginUi("name", "url")

        Truth.assertThat(model.name).isEqualTo("name")
        Truth.assertThat(model.url).isEqualTo("url")
    }
}