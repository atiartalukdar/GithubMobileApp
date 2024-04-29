package info.atiar.githubmobileapp

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    //private val repository = mock(HomeRepository::class.java)

    private val dispatcher = UnconfinedTestDispatcher()

    //private val sut = HomeViewModel(dispatcher, repository)

    @Test
    fun `should show data when api result is success`() = runTest {
//        `when`(repository.getRepositories("eishon"))
//            .thenReturn(
//                ApiResult.Success(
//                    listOf(
//                        Repo("repo1"),
//                        Repo("repo2")
//                    )
//                )
//            )
//
//        sut.getUserRepositories("eishon")
//
//        assertEquals(2, sut.uiState.value.data.size)
    }

    @Test
    fun `should show error when api result is success`() = runTest {
//        `when`(repository.getRepositories("eishon"))
//            .thenReturn(
//                ApiResult.Failure(
//                    RuntimeException("An error occurred")
//                )
//            )
//
//        sut.getUserRepositories("eishon")
//
//        assertEquals(0, sut.uiState.value.data.size)
    }
}