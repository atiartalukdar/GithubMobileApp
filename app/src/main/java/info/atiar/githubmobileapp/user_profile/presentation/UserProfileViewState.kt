package info.atiar.githubmobileapp.user_profile.presentation

import info.atiar.githubmobileapp.user_profile.domain.model.UserProfile
import info.atiar.githubmobileapp.user_profile.domain.model.UserRepos

data class UserProfileViewState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
    val userProfile: UserProfile = UserProfile(),
    val userRepos: List<UserRepos> = emptyList()
)