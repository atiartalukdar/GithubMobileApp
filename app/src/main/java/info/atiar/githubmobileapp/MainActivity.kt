package info.atiar.githubmobileapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import info.atiar.githubmobileapp.features.user_profile.ui.UserProfileView
import info.atiar.githubmobileapp.features.users.ui.UsersView
import info.atiar.githubmobileapp.utils.theme.GithubMobileAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubMobileAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {

                    val navController = rememberNavController()
                    NavHost(navController, startDestination = UsersView.route) {
                        composable(UsersView.route) {
                            UsersView.View(navController)
                        }
                        composable("${UserProfileView.route}/{userId}") { backStackEntry ->
                            val userId = backStackEntry.arguments?.getString("userId")
                            UserProfileView.View(navController, userId ?: "")
                        }
                    }
                }
            }
        }
    }
}