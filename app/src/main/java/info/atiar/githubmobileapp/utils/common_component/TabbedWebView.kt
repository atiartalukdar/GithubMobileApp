package info.atiar.githubmobileapp.utils.common_component

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent

fun tabbedWebView(url: String, context: Context) {
    val intent = CustomTabsIntent.Builder()
        .build()
    intent.launchUrl(context, Uri.parse(url))
}