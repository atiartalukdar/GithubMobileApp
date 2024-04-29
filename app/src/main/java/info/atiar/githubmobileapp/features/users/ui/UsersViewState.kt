package info.atiar.githubmobileapp.features.users.ui

import info.atiar.githubmobileapp.features.users.domain.model.User

data class UsersViewState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
    val users: List<User> = emptyList(),
)