package info.atiar.githubmobileapp.features.users.domain.model

data class User(
    val login: String = "",
    val id: Int = 0,
    val avatar_url: String = ""
) {
    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            "$login",
            "$id",
        )

        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}
