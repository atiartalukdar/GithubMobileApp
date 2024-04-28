package info.atiar.githubmobileapp.features.users.data.remote

import info.atiar.githubmobileapp.features.users.domain.model.User
import info.atiar.githubmobileapp.features.users.domain.model.UserSearch
import info.atiar.githubmobileapp.utils.EndPoints.USERS
import info.atiar.githubmobileapp.utils.EndPoints.USERS_SEARCH
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