package info.atiar.githubmobileapp.users.data.repositoryimpl

import info.atiar.githubmobileapp.users.data.remote.UsersApi
import info.atiar.githubmobileapp.users.domain.model.User
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
}
