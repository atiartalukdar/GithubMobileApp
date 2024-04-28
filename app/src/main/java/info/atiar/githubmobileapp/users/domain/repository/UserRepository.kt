package info.atiar.githubmobileapp.users.domain.repository

import info.atiar.githubmobileapp.users.domain.model.User
import info.atiar.githubmobileapp.utils.network_utils.ApiResult

interface UserRepository {
    suspend fun getUsers(): ApiResult<List<User>>
    suspend fun getUsersSearch(queries: String): ApiResult<List<User>>
}