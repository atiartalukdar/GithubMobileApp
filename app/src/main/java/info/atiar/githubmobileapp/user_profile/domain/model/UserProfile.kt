package info.atiar.githubmobileapp.user_profile.domain.model

data class UserProfile(
    val name: String = "",
    val login: String = "",
    val followers: Int = 0,
    val following: Int = 0,
    val avatar_url: String = ""
)
