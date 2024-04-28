package info.atiar.githubmobileapp.features.user_profile.ui

import info.atiar.githubmobileapp.features.user_profile.domain.model.UserProfile

data class UserProfileViewState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
    val userProfile: UserProfile = UserProfile()
)