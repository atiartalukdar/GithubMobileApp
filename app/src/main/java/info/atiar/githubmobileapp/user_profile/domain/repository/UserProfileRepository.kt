
import info.atiar.githubmobileapp.user_profile.domain.model.UserProfile
import info.atiar.githubmobileapp.user_profile.domain.model.UserRepos
import info.atiar.githubmobileapp.utils.network_utils.ApiResult

interface UserProfileRepository {
    suspend fun getUserProfile(userId: String): ApiResult<UserProfile>
    suspend fun getUserRepos(userId: String, isFork: Boolean): ApiResult<List<UserRepos>>
}