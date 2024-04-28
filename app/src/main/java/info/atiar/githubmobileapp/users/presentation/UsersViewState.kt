package info.atiar.githubmobileapp.users.presentation

import info.atiar.githubmobileapp.users.domain.model.User

data class UsersViewState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
    val users: List<User> = emptyList(),
)