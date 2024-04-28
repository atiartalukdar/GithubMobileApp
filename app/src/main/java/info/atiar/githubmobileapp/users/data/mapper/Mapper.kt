package info.atiar.githubmobileapp.users.data.mapper

import info.atiar.githubmobileapp.users.domain.model.ApiError
import info.atiar.githubmobileapp.users.domain.model.NetworkError
import retrofit2.HttpException
import java.io.IOException

fun Throwable.toNetworkError(): NetworkError {
    val error = when (this) {
        is IOException -> ApiError.NetworkError
        is HttpException -> ApiError.UnknownResponse
        else -> ApiError.UnknownError
    }

    return NetworkError(
        error = error,
        t = this
    )

}