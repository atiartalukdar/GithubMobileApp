package info.atiar.githubmobileapp.features.users.ui

import info.atiar.githubmobileapp.features.users.domain.model.User
import info.atiar.githubmobileapp.features.users.domain.repository.UserRepository
import info.atiar.githubmobileapp.utils.network_utils.ApiResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class UsersViewModelTest {
    private val repository = mock(UserRepository::class.java)
    private val dispatcher = UnconfinedTestDispatcher()
    private val sut = UsersViewModel(dispatcher, repository)

    @Test
    fun `should show data when users api result is success`() = runTest {
        Mockito.`when`(repository.getUsers())
            .thenReturn(
                ApiResult.Success(
                    listOf(
                        User("user1", 1, "https://github.com/image1"),
                        User("user2", 2, "https://github.com/image1")
                    )
                )
            )

        sut.getUsers()
        Assert.assertEquals(2, sut.state.value.users.size)
    }

    @Test
    fun `should show error when users api result is success`() = runTest {
        Mockito.`when`(repository.getUsers())
            .thenReturn(
                ApiResult.Failure(
                    RuntimeException("An error occurred")
                )
            )

        sut.getUsers()
        Assert.assertEquals(0, sut.state.value.users.size)
    }


    @Test
    fun `should show data when search api result is success`() = runTest {
        Mockito.`when`(repository.getUsersSearch("dummy_user"))
            .thenReturn(
                ApiResult.Success(
                    listOf(
                        User("user1", 1, "https://github.com/image1"),
                        User("user2", 2, "https://github.com/image1")
                    )
                )
            )

        sut.getUsersSearch("dummy_user")
        Assert.assertEquals(2, sut.state.value.users.size)
    }

    @Test
    fun `should show error when search api result is success`() = runTest {
        Mockito.`when`(repository.getUsersSearch("dummy_user"))
            .thenReturn(
                ApiResult.Failure(
                    RuntimeException("An error occurred")
                )
            )

        sut.getUsersSearch("dummy_user")
        Assert.assertEquals(0, sut.state.value.users.size)
    }

}