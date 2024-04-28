package info.atiar.githubmobileapp.users.presentation

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import info.atiar.githubmobileapp.users.domain.repository.UserRepository
import info.atiar.githubmobileapp.utils.Event
import info.atiar.githubmobileapp.utils.network_utils.ApiResult
import info.atiar.githubmobileapp.utils.sendEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _state = MutableStateFlow(UsersViewState())
    val state = _state.asStateFlow()

    init {
        //getUsers()
        getUsersSearch("defunkt")
    }

    @VisibleForTesting
    fun getUsers() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            when (val reposResult = userRepository.getUsers()) {
                is ApiResult.Success -> {
                    _state.value = UsersViewState(
                        isLoading = false,
                        users = reposResult.data
                    )
                }

                is ApiResult.Failure -> {
                    _state.value = UsersViewState(
                        isLoading = false,
                        isError = true,
                        errorMessage = reposResult.exception.message ?: "An error occurred"
                    )
                }
            }
        }
    }

    @VisibleForTesting
    fun getUsersSearch(queries: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            when (val reposResult = userRepository.getUsersSearch(queries)) {
                is ApiResult.Success -> {
                    _state.value = UsersViewState(
                        isLoading = false,
                        users = reposResult.data
                    )
                }

                is ApiResult.Failure -> {
                    _state.value = UsersViewState(
                        isLoading = false,
                        isError = true,
                        errorMessage = reposResult.exception.message ?: "An error occurred"
                    )
                }
            }
        }
    }
}