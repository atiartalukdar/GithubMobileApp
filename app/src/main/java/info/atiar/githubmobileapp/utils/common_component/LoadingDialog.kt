package info.atiar.githubmobileapp.utils.common_component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

/*
* LoadingDialog.kt
* Created by Atiar Talukdar on 2024/04/26.
*
* A medium article for fullscreen loading scree
* created by Atiar Talukdar
* https://medium.com/@aayan.talukdar/android-jetpack-creating-fullscreen-loading-dialog-using-jetpack-compose-410d3b011c7c
*
*/
@Composable
fun LoadingDialog(
    isShowingDialog: Boolean,
    dismissOnBackPress: Boolean = false,
    dismissOnClickOutside: Boolean = false,
) {
    if (isShowingDialog) {
        Dialog(
            onDismissRequest = { },
            DialogProperties(
                dismissOnBackPress = dismissOnBackPress,
                dismissOnClickOutside = dismissOnClickOutside,
            )
        ) {
            DialogContent()
        }
    }
}

@Composable
fun DialogContent() {
    Box(
        modifier = Modifier
            .size(76.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(4.dp),
            )
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center),
            color = Color.Blue,
        )
    }
}

@Preview
@Composable
fun PreviewSomeDialogContent() {
    Scaffold(
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray)
                    .padding(it),
                contentAlignment = Alignment.Center,
            ) {
                DialogContent()
            }
        }
    )
}