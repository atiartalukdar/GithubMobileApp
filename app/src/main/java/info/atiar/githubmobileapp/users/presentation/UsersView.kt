package info.atiar.githubmobileapp.users.presentation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import info.atiar.githubmobileapp.utils.common_component.AppBar
import info.atiar.githubmobileapp.utils.common_component.LoadingDialog

@Composable
internal fun UsersView(
    viewModel: UsersViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    UsersContent(state = state)
}


@Composable
fun UsersContent(
    state: UsersViewState
) {
    LoadingDialog(isShowingDialog = state.isLoading)
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { AppBar(title = "Users") },
    ) {
        LazyColumn(
            modifier = Modifier.padding(top = it.calculateTopPadding())
        ) {
            items(state.users) { user ->
                Row(
                    Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = user.avatar_url,
                        contentDescription = user.login,
                        modifier = Modifier
                            .clip(CircleShape)
                            .height(60.dp)
                            .width(80.dp),
                        contentScale = ContentScale.Fit,
                    )
                    Text(
                        text = user.login, style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}