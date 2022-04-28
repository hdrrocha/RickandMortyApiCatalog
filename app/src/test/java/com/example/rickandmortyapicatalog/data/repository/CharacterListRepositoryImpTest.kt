import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.Pager
import androidx.paging.PagingData
import com.example.rickandmortyapicatalog.data.model.CharacterInfo
import com.example.rickandmortyapicatalog.data.repository.CharacterListRepositoryImp
import com.example.rickandmortyapicatalog.domain.repository.CharacterListRepository
import com.example.rickandmortyapicatalog.helper.CharacterListFactory
import com.example.rickandmortyapicatalog.helper.CoroutinesTestRule
import com.example.rickandmortyapicatalog.helper.PagingDataTest
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CharacterListRepositoryImpTest : PagingDataTest() {

    @get:Rule
    val instantTaskExecutor = InstantTaskExecutorRule()

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineRule = CoroutinesTestRule()

    @MockK
    private lateinit var mockPager: Pager<Int, CharacterInfo>

    private lateinit var sut: CharacterListRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        sut = CharacterListRepositoryImp(pager = mockPager)
    }

    @Test
    fun fetchImagesSuccessTest() = runBlocking {
        val expected = CharacterListFactory.characterList
        every { mockPager.flow }.returns(flowOf(PagingData.from(expected)))

        sut.fetchCharacter().collect {
            val actual = getDifferSnapshot(it)

            assertThat(actual).isNotEmpty()
            assertThat(actual.items).isNotEmpty()
            assertThat(actual.items).isEqualTo(expected)
        }
    }

    @Test
    fun fetchImagesErrorTest() = runBlocking {
        every { mockPager.flow }.returns(flowOf(PagingData.empty()))

        val data = sut.fetchCharacter().singleOrNull()
        val actual = getDifferSnapshot(data!!)

        assertThat(actual).isEmpty()
    }
}
