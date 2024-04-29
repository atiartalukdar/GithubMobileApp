package info.atiar.githubmobileapp.user_profile.domain.model

data class UserRepo(
    val name: String = "",
    val fork: Boolean = false,
    val language: String? = "",
    val description: String? = "",
    val stargazers_count: Int = 0,
    val html_url: String = ""
)
