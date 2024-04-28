package info.atiar.githubmobileapp.users.presentation

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import info.atiar.githubmobileapp.users.domain.repository.UserRepository
import info.atiar.githubmobileapp.utils.network_utils.ApiResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _state = MutableStateFlow(UsersViewState())
    val state = _state.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _users = flowOf(state.value.users)

    val users = _searchText
        .onEach { _isSearching.update { true } }
        .combine(_users) { text, users ->
            if (text.isBlank()) {
                users
            } else {
                getUsersSearch(text)
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            state.value.users
        )

    fun onSearchTextChange(text: String) {
        _searchText.value = text
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