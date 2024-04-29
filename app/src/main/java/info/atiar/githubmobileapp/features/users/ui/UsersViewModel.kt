package info.atiar.githubmobileapp.features.users.ui

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import info.atiar.githubmobileapp.features.users.domain.repository.UserRepository
import info.atiar.githubmobileapp.utils.network_utils.ApiResult
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
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
    private var searchJob: Job? = null

    fun searchWithDebounce(query: String) {
        searchJob?.cancel() // Cancel previous search job if it exists
        searchJob = viewModelScope.launch {
            delay(700) // Debounce time
            if (query.isNotEmpty()) {
                getUsersSearch(query)
            } else {
                getUsers()
            }
        }
    }

    init {
        getUsers()
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