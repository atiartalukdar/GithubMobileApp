package info.atiar.githubmobileapp.users.domain.model

data class User(
    val login: String,
    val id: Int,
    val avatar_url: String
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
