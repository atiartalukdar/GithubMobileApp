package info.atiar.githubmobileapp.features.users.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun SearchIcon() {
    Icon(
        imageVector = Icons.Default.Search,
        contentDescription = "Search Text",
        modifier = Modifier
            .padding(8.dp)
    )
}

@Composable
fun RightCrossIcon(onIconClick: () -> Unit) {
    Icon(
        imageVector = Icons.Default.Close,
        contentDescription = "Clear Text",
        modifier = Modifier
            .padding(8.dp)
            .clickable { onIconClick() }
    )
}