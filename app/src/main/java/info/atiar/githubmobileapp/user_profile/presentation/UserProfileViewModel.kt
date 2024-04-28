package info.atiar.githubmobileapp.user_profile.presentation

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import info.atiar.githubmobileapp.user_profile.domain.repository.UserProfileRepository
import info.atiar.githubmobileapp.utils.network_utils.ApiResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val userProfileRepository: UserProfileRepository
) : ViewModel() {

    private val _state = MutableStateFlow(UserProfileViewState())
    val state = _state.asStateFlow()

    init {
        //TODO call with userID
        //getUserProfile("defunkt")
        //getUserRepo("defunkt")
    }

    @VisibleForTesting
    fun getUserProfile(userId: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            when (val reposResult = userProfileRepository.getUserProfile(userId)) {
                is ApiResult.Success -> {
                    _state.value = UserProfileViewState(
                        isLoading = false,
                        userProfile = reposResult.data
                    )
                }

                is ApiResult.Failure -> {
                    _state.value = UserProfileViewState(
                        isLoading = false,
                        isError = true,
                        errorMessage = reposResult.exception.message ?: "An error occurred"
                    )
                }
            }
        }
    }

    @VisibleForTesting
    fun getUserRepo(userId: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            when (val reposResult = userProfileRepository.getUserRepos(userId, isFork = false)) {
                is ApiResult.Success -> {
                    _state.value = UserProfileViewState(
                        isLoading = false,
                        userRepos = reposResult.data
                    )
                }

                is ApiResult.Failure -> {
                    _state.value = UserProfileViewState(
                        isLoading = false,
                        isError = true,
                        errorMessage = reposResult.exception.message ?: "An error occurred"
                    )
                }
            }
        }
    }
}