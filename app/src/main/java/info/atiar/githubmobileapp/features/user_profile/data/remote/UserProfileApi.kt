package info.atiar.githubmobileapp.features.user_profile.data.remote

import info.atiar.githubmobileapp.features.user_profile.domain.model.UserProfile
import info.atiar.githubmobileapp.user_profile.domain.model.UserRepo
import retrofit2.http.GET
import retrofit2.http.Path

interface UserProfileApi {
    @GET("users/{user}")
    suspend fun getUserProfile(@Path("user") userId: String): UserProfile

    @GET("users/{user}/repos")
    suspend fun getUserRepos(@Path("user") userId: String): List<UserRepo>
}