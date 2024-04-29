package info.atiar.githubmobileapp.features.user_profile.ui.components

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import info.atiar.githubmobileapp.R
import info.atiar.githubmobileapp.features.user_profile.domain.model.UserProfile

@Composable
fun UserView(
    user: UserProfile,
    onBackPressed: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(12.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            //back arrow
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "Back Button",
                    tint = Color.White,
                    modifier = Modifier.clickable {
                        onBackPressed()
                    }
                )
                Text(
                    text = "Back",
                    color = Color.White,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .clickable {
                            onBackPressed()
                        }
                )
            }

            AsyncImage(
                model = user.avatar_url,
                contentDescription = user.login,
                modifier = Modifier
                    .padding(8.dp)
                    .clip(CircleShape)
                    .height(112.dp)
                    .width(112.dp)
                    .background(Color.White),
                contentScale = ContentScale.Crop,
            )

            user.name?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White
                )
            }

            Text(
                text = "@" + user.login,
                style = MaterialTheme.typography.labelMedium,
                color = Color.White
            )

            //Followers and follwoing
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Icon(
                    painterResource(id = R.drawable.followers),
                    contentDescription = "followers",
                    modifier = Modifier
                        .width(20.dp)
                        .height(20.dp),
                    tint = Color.White
                )
                Text(
                    text = "${user.followers} Followers",
                    color = Color.White,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 8.dp)
                )

                Spacer(modifier = Modifier.padding(20.dp))

                Icon(
                    painterResource(id = R.drawable.followers),
                    contentDescription = "Following",
                    modifier = Modifier
                        .width(20.dp)
                        .height(20.dp),
                    tint = Color.White,
                )
                Text(
                    text = "${user.following} Following",
                    color = Color.White,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewUserView() {

    val userProfile = UserProfile(
        name = "Atiar Talukdar",
        login = "atiartalukdar",
        followers = 100,
        following = 99,
        avatar_url = "https://avatars.githubusercontent.com/u/15125407?v=4"
    )

    Scaffold(
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray)
                    .padding(it),
                contentAlignment = Alignment.Center,
            ) {
                UserView(userProfile) {

                }
            }
        }
    )
}