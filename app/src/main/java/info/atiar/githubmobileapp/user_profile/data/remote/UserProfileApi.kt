package info.atiar.githubmobileapp.user_profile.data.remote

import info.atiar.githubmobileapp.user_profile.domain.model.UserProfile
import info.atiar.githubmobileapp.utils.EndPoints.USER_PROFILE
import retrofit2.http.GET
import retrofit2.http.Path

interface UserProfileApi {
    @GET(USER_PROFILE)
    suspend fun getUserProfile(@Path("user") userId: String): UserProfile
}