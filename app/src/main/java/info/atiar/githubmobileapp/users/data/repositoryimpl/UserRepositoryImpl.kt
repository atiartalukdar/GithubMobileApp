package info.atiar.githubmobileapp.users.data.repositoryimpl

import info.atiar.githubmobileapp.users.data.remote.UsersApi
import info.atiar.githubmobileapp.users.domain.model.User
import info.atiar.githubmobileapp.user_profile.domain.model.UserProfile
import info.atiar.githubmobileapp.users.domain.repository.UserRepository
import info.atiar.githubmobileapp.utils.network_utils.ApiResult

class UserRepositoryImpl(
    private val usersApi: UsersApi
) : UserRepository {
    override suspend fun getUsers(): ApiResult<List<User>> {
        return try {
            val repos = usersApi.getUsers()
            ApiResult.Success(repos)
        } catch (e: Exception) {
            ApiResult.Failure(e)
        }
    }
    override suspend fun getUsersSearch(queries: String): ApiResult<List<User>> {
        return try {
            val repos = usersApi.getUsersSearch(queries)
            ApiResult.Success(repos.items)
        } catch (e: Exception) {
            ApiResult.Failure(e)
        }
    }
}
