package info.atiar.githubmobileapp.users.data.repositoryimpl

import arrow.core.Either
import info.atiar.githubmobileapp.users.data.mapper.toNetworkError
import info.atiar.githubmobileapp.users.data.remote.UsersApi
import info.atiar.githubmobileapp.users.domain.model.NetworkError
import info.atiar.githubmobileapp.users.domain.model.User
import info.atiar.githubmobileapp.users.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val usersApi: UsersApi
) : UserRepository {
    override suspend fun getUsers(): Either<NetworkError, List<User>> {
        return Either.catch {
            usersApi.getUsers()
        }.mapLeft { it.toNetworkError() }
    }
}