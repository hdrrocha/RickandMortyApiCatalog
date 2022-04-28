package com.example.rickandmortyapicatalog.data.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.rickandmortyapicatalog.helper.BaseUnitTest
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.HttpException
import java.net.HttpURLConnection

class RickAndMortyApiTest : BaseUnitTest() {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var sut: RickAndMortyApi

    @Before
    fun start() {
        sut = buildApi()
    }

    @Test
    fun fetchCharacterSuccessTest() = runBlocking {
        mockNetworkResponseWithFileContent("response200.json", HttpURLConnection.HTTP_OK)

        val actual = sut.fetchCharacter()
        Truth.assertThat(actual.info.count).isEqualTo(826)
        Truth.assertThat(actual.info.pages).isEqualTo(42)
        Truth.assertThat(actual.info.next).isEqualTo("https://rickandmortyapi.com/api/character/?page=2")
        Truth.assertThat(actual.info.prev).isNull()
        Truth.assertThat(actual.results[0].id).isEqualTo(1)
        Truth.assertThat(actual.results[0].name).isEqualTo("Rick Sanchez")
        Truth.assertThat(actual.results[0].status).isEqualTo("Alive")
        Truth.assertThat(actual.results[0].species).isEqualTo("Human")
        Truth.assertThat(actual.results[0].type).isEqualTo("")
        Truth.assertThat(actual.results[0].gender).isEqualTo("Male")
        Truth.assertThat(actual.results[0].origin?.name).isEqualTo("Earth (C-137)")
        Truth.assertThat(actual.results[0].origin?.url).isEqualTo("https://rickandmortyapi.com/api/location/1")
        Truth.assertThat(actual.results[0].location?.name).isEqualTo("Earth (C-137)")
        Truth.assertThat(actual.results[0].location?.url).isEqualTo("https://rickandmortyapi.com/api/location/1")
        Truth.assertThat(actual.results[0].image).isEqualTo("https://rickandmortyapi.com/api/character/avatar/1.jpeg")
        Truth.assertThat(actual.results[0].episode).isEmpty()
        Truth.assertThat(actual.results[0].url).isEqualTo("https://rickandmortyapi.com/api/character/1")
        Truth.assertThat(actual.results[0].created).isEqualTo("2017-11-04T18:48:46.250Z")

    }


    @Test(expected = HttpException::class)
    fun getScoreErrorTest() {
        runBlocking {
            mockNetworkResponseWithFileContent(
                "response200.json",
                HttpURLConnection.HTTP_BAD_REQUEST
            )
            sut.fetchCharacter()
        }
    }
}

