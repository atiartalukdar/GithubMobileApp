package info.atiar.githubmobileapp.users.presentation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import info.atiar.githubmobileapp.utils.common_component.LoadingDialog

@Composable
internal fun UsersView(
    viewModel: UsersViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(key1 = state.searchText.value) {
        Log.e("Atiar, search text", state.searchText.value)
        viewModel.onSearchTextChange(state.searchText.value)
    }

    UsersContent(state = state)
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersContent(
    state: UsersViewState,
) {
    var query by remember { mutableStateOf(state.searchText.value) }
    var active by remember { mutableStateOf(false) }

    LoadingDialog(isShowingDialog = state.isLoading)
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)

        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Top
            ) {
                DockedSearchBar(
                    query = query,
                    onQueryChange = { newValue ->
                        query = newValue
                        state.searchText.value = newValue
                    },
                    onSearch = { newQuery ->
                        println("Performing search on query: $newQuery")
                        state.searchText.value = newQuery
                    },
                    active = active,
                    onActiveChange = { active = it },
                    placeholder = {
                        Text(text = "Search")
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
                    },
                    trailingIcon = {
                        Row {
                            if (active) {
                                IconButton(
                                    onClick = {
                                        if (query.isNotEmpty()) query = "" else active = false
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Close,
                                        contentDescription = "Close"
                                    )
                                }
                            }
                        }
                    }
                ) {
                    // This is where the content of the search bar would go
                }

            }

            LazyColumn {
                items(state.users) { user ->
                    Row(
                        Modifier.padding(vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            model = user.avatar_url,
                            contentDescription = user.login,
                            modifier = Modifier
                                .height(60.dp)
                                .width(80.dp)
                                .clip(CircleShape),
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
}