package info.atiar.githubmobileapp.features.user_profile.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import info.atiar.githubmobileapp.features.user_profile.domain.model.UserProfile
import info.atiar.githubmobileapp.features.user_profile.ui.components.RepoItemView
import info.atiar.githubmobileapp.features.user_profile.ui.components.UserView
import info.atiar.githubmobileapp.user_profile.domain.model.UserRepo
import info.atiar.githubmobileapp.utils.common_component.LoadingDialog
import info.atiar.githubmobileapp.utils.common_component.tabbedWebView


object UserProfileView {
    val route: String = javaClass.simpleName

    @Composable
    internal fun View(
        navController: NavController,
        userId: String,
        viewModel: UserProfileViewModel = hiltViewModel()
    ) {
        var shouldFetchData by remember { mutableStateOf(true) }
        LaunchedEffect(Unit) {
            if (shouldFetchData) {
                viewModel.fetchData(userId)
                shouldFetchData = false
            }
        }

        val state by viewModel.state.collectAsStateWithLifecycle()
        UserProfileContent(state = state) {
            navController.popBackStack()
        }
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun UserProfileContent(
        state: UserProfileViewState,
        onBackPressed: () -> Unit

    ) {
        LoadingDialog(isShowingDialog = state.isLoading)
        val context = LocalContext.current

        Scaffold(
            modifier = Modifier.fillMaxSize(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top
            ) {
                UserView(state.userProfile) {
                    onBackPressed()
                }

                Text(
                    text = "Repositories (${state.userRepos.size})",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                )

                LazyColumn(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(state.userRepos) {
                        RepoItemView(userRepo = it) { repoUrl ->
                            //TODO: Bring the web-view as Util function
                            tabbedWebView(repoUrl, context)
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewUserProfile() {

    val userProfile = UserProfile(
        name = "Atiar Talukdar",
        login = "atiartalukdar",
        followers = 100,
        following = 99,
        avatar_url = "https://avatars.githubusercontent.com/u/15125407?v=4"
    )

    val usersRepo = UserRepo(
        name = "Github Mobile App",
        fork = false,
        language = "Kotlin",
        description = "MVVM, Clear architecture, SOLID principal, Dagger hilt, JetPack compose, Retrofit",
        stargazers_count = 10000
    )

    val usersRepos = listOf(usersRepo, usersRepo, usersRepo)

    Scaffold(
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray)
                    .padding(it),
                contentAlignment = Alignment.Center,
            ) {
                UserProfileView.UserProfileContent(
                    UserProfileViewState(userProfile = userProfile, userRepos = usersRepos)
                ) {

                }
            }
        }
    )
}