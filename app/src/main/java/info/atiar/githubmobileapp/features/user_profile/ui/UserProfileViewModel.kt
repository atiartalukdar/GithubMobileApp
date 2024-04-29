package info.atiar.githubmobileapp.features.user_profile.ui

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import info.atiar.githubmobileapp.features.user_profile.domain.repository.UserProfileRepository
import info.atiar.githubmobileapp.utils.network_utils.ApiResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    @Named("main_dispatcher") private val dispatcher: CoroutineDispatcher,
    private val userProfileRepository: UserProfileRepository
) : ViewModel() {

    private val _state = MutableStateFlow(UserProfileViewState())
    val state = _state.asStateFlow()

    fun fetchData(userId: String) {
        getUserProfile(userId)
        getUserRepo(userId)
    }

    @VisibleForTesting
    fun getUserProfile(userId: String) {
        viewModelScope.launch(dispatcher) {
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
        viewModelScope.launch(dispatcher) {
            _state.update { it.copy(isLoading = true) }

            when (val reposResult = userProfileRepository.getUserRepos(userId, isFork = false)) {
                is ApiResult.Success -> {
                    _state.update { it.copy(userRepos = reposResult.data) }
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