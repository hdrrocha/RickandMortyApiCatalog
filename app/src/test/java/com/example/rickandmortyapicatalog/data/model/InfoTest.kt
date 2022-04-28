package com.example.rickandmortyapicatalog.data.model

import com.example.rickandmortyapicatalog.helper.BaseUnitTest
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Test

class InfoTest : BaseUnitTest() {
    @Test
    fun constructor() = runBlocking {
        val model = Info(1, "2", 10, "prev")

        Truth.assertThat(model.count).isEqualTo(1)
        Truth.assertThat(model.next).isEqualTo("2")
        Truth.assertThat(model.pages).isEqualTo(10)
        Truth.assertThat(model.prev).isEqualTo("prev")

    }
}