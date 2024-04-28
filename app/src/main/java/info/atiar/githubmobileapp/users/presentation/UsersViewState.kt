package info.atiar.githubmobileapp.users.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import info.atiar.githubmobileapp.users.domain.model.User

data class UsersViewState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
    val users: List<User> = emptyList(),
    val error: String? = null,
    var searchText: MutableState<String> = mutableStateOf("")
)