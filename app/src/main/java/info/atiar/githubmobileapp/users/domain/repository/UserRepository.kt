package info.atiar.githubmobileapp.users.domain.repository

import arrow.core.Either
import info.atiar.githubmobileapp.users.domain.model.NetworkError
import info.atiar.githubmobileapp.users.domain.model.User

interface UserRepository {
    suspend fun getUsers(): Either<NetworkError, List<User>>
}