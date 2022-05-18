package com.example.rickandmortyapicatalog.data.model

import com.example.rickandmortyapicatalog.helper.BaseUnitTest
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Test

class LocationTest : BaseUnitTest() {
    @Test
    fun constructor() = runBlocking {
        val model = Location("name", "url")

        Truth.assertThat(model.name).isEqualTo("name")
        Truth.assertThat(model.url).isEqualTo("url")
    }
}