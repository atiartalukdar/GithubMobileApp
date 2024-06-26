package info.atiar.githubmobileapp.features.users.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import info.atiar.githubmobileapp.features.user_profile.ui.UserProfileView
import info.atiar.githubmobileapp.features.users.ui.components.RightCrossIcon
import info.atiar.githubmobileapp.features.users.ui.components.SearchIcon
import info.atiar.githubmobileapp.utils.common_component.LoadingDialog

object UsersView {
    val route: String = javaClass.simpleName

    @Composable
    internal fun View(
        navController: NavController,
        viewModel: UsersViewModel = hiltViewModel()
    ) {

        var shouldFetchData by remember { mutableStateOf(true) }
        LaunchedEffect(Unit) {
            if (shouldFetchData) {
                viewModel.fetchData()
                shouldFetchData = false
            }
        }

        val state by viewModel.state.collectAsStateWithLifecycle()
        UsersContent(
            state = state,
            searchQuery = { query ->
                viewModel.searchWithDebounce(query)
            },
            onItemClick = { data ->
                navController.navigate("${UserProfileView.route}/$data")
            }
        )
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun UsersContent(
        state: UsersViewState,
        searchQuery: (String) -> Unit,
        onItemClick: (String) -> Unit

    ) {
        var query by remember { mutableStateOf("") }

        LoadingDialog(isShowingDialog = state.isLoading)
        Scaffold(
            modifier = Modifier.fillMaxSize(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                //Search bar
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(8.dp)

                ) {
                    TextField(
                        value = query,
                        onValueChange = { newValue ->
                            query = newValue
                            searchQuery(query)
                        },
                        label = { Text("Search") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp),
                        trailingIcon = {
                            if (query.isNotEmpty()) {
                                RightCrossIcon {
                                    query = ""
                                    searchQuery(query)
                                }
                            } else {
                                SearchIcon()
                            }
                        },
                    )
                }

                Text(
                    text = "${if (query.isNotEmpty()) "Search Results" else "Users"} (${state.users.size})",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                )

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    items(state.users) { user ->
                        Box(
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                                .fillMaxWidth()
                                .background(
                                    color = Color(0xFFDFF3FF),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .clickable {
                                    onItemClick(user.login)
                                }
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // Circular avatar
                                AsyncImage(
                                    model = user.avatar_url,
                                    contentDescription = user.login,
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .clip(CircleShape)
                                        .height(56.dp)
                                        .width(56.dp),
                                    contentScale = ContentScale.Crop,
                                )
                                Text(
                                    text = user.login,
                                    style = MaterialTheme.typography.titleMedium,
                                    modifier = Modifier.padding(start = 8.dp)
                                )
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }

            }
        }
    }
}
