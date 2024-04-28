package info.atiar.githubmobileapp.features.users.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import info.atiar.githubmobileapp.features.users.domain.model.User

data class UsersViewState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
    val users: List<User> = emptyList(),
    var searchText: MutableState<String> = mutableStateOf("")
)