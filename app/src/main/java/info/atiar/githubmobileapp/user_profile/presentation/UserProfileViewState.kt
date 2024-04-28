package info.atiar.githubmobileapp.user_profile.presentation

import info.atiar.githubmobileapp.user_profile.domain.model.UserProfile

data class UserProfileViewState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
    val userProfile: UserProfile = UserProfile()
)