package info.atiar.githubmobileapp.user_profile.data.repositoryimpl

import info.atiar.githubmobileapp.user_profile.data.remote.UserProfileApi
import info.atiar.githubmobileapp.users.data.remote.UsersApi
import info.atiar.githubmobileapp.users.domain.model.User
import info.atiar.githubmobileapp.user_profile.domain.model.UserProfile
import info.atiar.githubmobileapp.user_profile.domain.repository.UserProfileRepository
import info.atiar.githubmobileapp.users.domain.repository.UserRepository
import info.atiar.githubmobileapp.utils.network_utils.ApiResult

class UserProfileRepositoryImpl(
    private val userProfileApi: UserProfileApi
) : UserProfileRepository {
    override suspend fun getUserProfile(userId: String): ApiResult<UserProfile> {
        return try {
            val repos = userProfileApi.getUserProfile(userId)
            ApiResult.Success(repos)
        } catch (e: Exception) {
            ApiResult.Failure(e)
        }
    }
}
