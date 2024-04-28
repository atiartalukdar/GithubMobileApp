package info.atiar.githubmobileapp.users.data.remote

import info.atiar.githubmobileapp.users.domain.model.User
import info.atiar.githubmobileapp.users.domain.model.UserSearch
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersApi {
    @GET("users")
    suspend fun getUsers(): List<User>

    @GET("search/users")
    suspend fun getUsersSearch(@Query("q") queries: String): UserSearch
}