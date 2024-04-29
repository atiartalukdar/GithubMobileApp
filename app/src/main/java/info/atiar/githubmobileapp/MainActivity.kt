package info.atiar.githubmobileapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import info.atiar.githubmobileapp.features.user_profile.ui.UserProfileView
import info.atiar.githubmobileapp.utils.Event
import info.atiar.githubmobileapp.utils.EventBus
import info.atiar.githubmobileapp.utils.theme.GithubMobileAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val lifecycleOwner = LocalLifecycleOwner.current.lifecycle
            LaunchedEffect(key1 = lifecycleOwner) {
                lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    EventBus.events.collect { event ->
                        when (event) {
                            is Event.Toast -> {
                                Toast.makeText(
                                    this@MainActivity,
                                    event.message,
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }

                    }
                }
            }

            GithubMobileAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    //UsersView()
                    UserProfileView()
                }
            }
        }
    }
}