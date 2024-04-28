package info.atiar.githubmobileapp.users.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import info.atiar.githubmobileapp.users.domain.repository.UserRepository
import info.atiar.githubmobileapp.utils.Event
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
        getUsers()
    }

    private fun getUsers() {
        viewModelScope.launch {
            //Turning off Loading
            _state.update { it.copy(isLoading = true) }

            //Getting Users
            userRepository.getUsers()
                .onRight { users ->
                    _state.update { it.copy(users = users) }
                }
                .onLeft { error ->
                    _state.update { it.copy(error = error.error.message) }
                    sendEvent(Event.Toast(error.error.message))
                }

            //Turning off Loading
            _state.update { it.copy(isLoading = false) }
        }
    }
}