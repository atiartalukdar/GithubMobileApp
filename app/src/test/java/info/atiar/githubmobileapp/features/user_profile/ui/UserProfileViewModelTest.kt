package info.atiar.githubmobileapp.features.user_profile.ui

import info.atiar.githubmobileapp.features.user_profile.domain.model.UserProfile
import info.atiar.githubmobileapp.features.user_profile.domain.repository.UserProfileRepository
import info.atiar.githubmobileapp.user_profile.domain.model.UserRepo
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
class UserProfileViewModelTest {
    private val repository = mock(UserProfileRepository::class.java)
    private val dispatcher = UnconfinedTestDispatcher()
    private val sut = UserProfileViewModel(dispatcher, repository)

    @Test
    fun `should show data when user profile api result is success`() = runTest {
        Mockito.`when`(repository.getUserProfile("dummy_user"))
            .thenReturn(
                ApiResult.Success(
                    UserProfile("user1", "user1", 1, 10, "https://github.com/image1")
                )
            )

        sut.getUserProfile("dummy_user")
        Assert.assertEquals("user1", sut.state.value.userProfile.name)
    }

    @Test
    fun `should show error when user profile  api result is success`() = runTest {
        Mockito.`when`(repository.getUserProfile("dummy_user"))
            .thenReturn(
                ApiResult.Failure(
                    RuntimeException("An error occurred")
                )
            )

        sut.getUserProfile("dummy_user")
        Assert.assertEquals("", sut.state.value.userProfile.name)
    }


    @Test
    fun `should show data when user repo api result is success`() = runTest {
        Mockito.`when`(repository.getUserRepos("dummy_user", false))
            .thenReturn(
                ApiResult.Success(
                    listOf(
                        UserRepo(
                            "user1",
                            false,
                            "Java",
                            "Dummy description",
                            1,
                            "https://github.com/image1"
                        ),
                        UserRepo(
                            "user2",
                            false,
                            "Kotlin",
                            "Dummy description",
                            1,
                            "https://github.com/image2"
                        )
                    )
                )
            )

        sut.getUserRepo("dummy_user")
        Assert.assertEquals(2, sut.state.value.userRepos.size)
    }

    @Test
    fun `should show error when user repo api result is success`() = runTest {
        Mockito.`when`(repository.getUserRepos("dummy_user", false))
            .thenReturn(
                ApiResult.Failure(
                    RuntimeException("An error occurred")
                )
            )

        sut.getUserRepo("dummy_user")
        Assert.assertEquals(0, sut.state.value.userRepos.size)
    }

}