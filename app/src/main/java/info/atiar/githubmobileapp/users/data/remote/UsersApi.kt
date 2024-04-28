package info.atiar.githubmobileapp.users.data.remote

import info.atiar.githubmobileapp.users.domain.model.User
import info.atiar.githubmobileapp.utils.EndPoints.USERS
import retrofit2.http.GET

interface UsersApi {
    @GET(USERS)
    suspend fun getUsers(): List<User>
}