package info.atiar.githubmobileapp.user_profile.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import info.atiar.githubmobileapp.utils.common_component.AppBar
import info.atiar.githubmobileapp.utils.common_component.LoadingDialog

@Composable
internal fun UserProfileView(
    viewModel: UserProfileViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    UserProfileContent(state = state)
}

@Composable
fun UserProfileContent(
    state: UserProfileViewState
) {
    LoadingDialog(isShowingDialog = state.isLoading)
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { AppBar(title = "Users") },
    ) {
        LazyColumn(
            modifier = Modifier.padding(top = it.calculateTopPadding())
        ) {
            //TODO UI modification
        }
    }
}