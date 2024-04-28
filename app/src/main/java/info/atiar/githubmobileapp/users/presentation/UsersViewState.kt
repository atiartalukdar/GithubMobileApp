package info.atiar.githubmobileapp.users.presentation

import info.atiar.githubmobileapp.users.domain.model.User

data class UsersViewState(
    val isLoading: Boolean = false,
    val users: List<User> = emptyList(),
    val error: String? = null
)