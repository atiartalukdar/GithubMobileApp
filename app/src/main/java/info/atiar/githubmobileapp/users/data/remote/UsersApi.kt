package info.atiar.githubmobileapp.users.data.remote

import info.atiar.githubmobileapp.users.domain.model.User
import info.atiar.githubmobileapp.utils.EndPoints.USERS
import retrofit2.http.GET
import retrofit2.http.Path

interface UsersApi {
    @GET(USERS)
    suspend fun getUsers(): List<User>
}