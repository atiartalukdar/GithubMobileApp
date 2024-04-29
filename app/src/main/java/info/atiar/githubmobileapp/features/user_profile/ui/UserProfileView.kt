package info.atiar.githubmobileapp.features.user_profile.ui

import android.annotation.SuppressLint
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebView
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import info.atiar.githubmobileapp.features.user_profile.domain.model.UserProfile
import info.atiar.githubmobileapp.features.user_profile.ui.components.RepoItemView
import info.atiar.githubmobileapp.features.user_profile.ui.components.UserView
import info.atiar.githubmobileapp.user_profile.domain.model.UserRepo
import info.atiar.githubmobileapp.utils.common_component.LoadingDialog

@Composable
internal fun UserProfileView(
    viewModel: UserProfileViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    UserProfileContent(state = state)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UserProfileContent(
    state: UserProfileViewState
) {
    var url by remember { mutableStateOf("") }
    var shouldOpenWebview by remember { mutableStateOf(false) }
    LoadingDialog(isShowingDialog = state.isLoading)
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            UserView(state.userProfile)

            Text(
                text = "Repositories",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
            )

            LazyColumn(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(state.userRepos) {
                    RepoItemView(userRepo = it) { repoUrl ->
                        Log.e("Git Repo Link:", repoUrl)
                        url = repoUrl
                        shouldOpenWebview = true
                    }
                }
            }
        }
    }

    if (shouldOpenWebview) {
        InAppWebView(url = url) {
            if (it) {
                shouldOpenWebview = false
            }
        }
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun InAppWebView(url: String, onReleaseWebview: (Boolean) -> Unit) {
    AndroidView(
        factory = ::WebView,
        update = { webView ->
            webView.webChromeClient = WebChromeClient()
            webView.settings.javaScriptEnabled = true
            webView.loadUrl(url)
        },
        onRelease = {
            onReleaseWebview(true)
        }
    )
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
        stargazers_count = 1000000
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
                UserProfileContent(
                    UserProfileViewState(userProfile = userProfile, userRepos = usersRepos)
                )
            }
        }
    )
}