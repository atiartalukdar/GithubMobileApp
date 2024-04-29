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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import info.atiar.githubmobileapp.R
import info.atiar.githubmobileapp.user_profile.domain.model.UserRepo

@Composable
fun RepoItemView(
    userRepo: UserRepo,
    onItemClick: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .clickable() {
                onItemClick(userRepo.html_url)
            }
            .fillMaxWidth()
            .background(Color(0xFFDFF3FF), shape = RoundedCornerShape(8.dp))
            .padding(12.dp)

    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = userRepo.name,
                    color = Color.Black,
                    modifier = Modifier
                        .weight(1f),
                    maxLines = 2,
                    style = MaterialTheme.typography.titleMedium,
                    overflow = TextOverflow.Ellipsis,
                )

                Icon(
                    painterResource(id = R.drawable.open_new_link),
                    contentDescription = "Open in Webview",
                    tint = Color.Black,
                    modifier = Modifier
                        .height(20.dp)
                        .width(20.dp)

                )
            }

            userRepo.description?.let {
                Text(
                    text = it,
                    color = Color.Black,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth(),
                    maxLines = 6,
                    textAlign = TextAlign.Start,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {

                Icon(
                    Icons.Default.Star,
                    contentDescription = "Star in Repo",
                    tint = Color.Black,
                    modifier = Modifier
                        .height(20.dp)
                        .width(20.dp)

                )

                Text(
                    text = "${userRepo.stargazers_count} Stars",
                    color = Color.Black,
                    modifier = Modifier
                        .padding(start = 8.dp),
                    maxLines = 1,
                    style = MaterialTheme.typography.bodySmall,
                    overflow = TextOverflow.Ellipsis,
                )

                Spacer(modifier = Modifier.padding(horizontal = 10.dp))


                userRepo.language?.let {
                    Icon(
                        painterResource(id = R.drawable.programing_language),
                        contentDescription = "Programing Language",
                        tint = Color.Black,
                        modifier = Modifier
                            .height(20.dp)
                            .width(20.dp)
                            .padding(2.dp) //to adjust the feeling of other UI

                    )

                    Text(
                        text = it,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(start = 6.dp),
                        maxLines = 1,
                        style = MaterialTheme.typography.bodySmall,
                        overflow = TextOverflow.Ellipsis,
                    )
                }


            }
        }
    }
}

@Preview
@Composable
fun PreviewUserRepoView() {

    val usersRepo = UserRepo(
        name = "Github Mobile App",
        fork = false,
        language = "Kotlin",
        description = "MVVM, Clear architecture, SOLID principal, Dagger hilt, JetPack compose, Retrofit",
        stargazers_count = 998
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
                RepoItemView(usersRepo) {

                }
            }
        }
    )
}