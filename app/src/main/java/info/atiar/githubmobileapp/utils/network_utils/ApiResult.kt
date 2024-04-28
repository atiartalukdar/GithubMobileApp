package info.atiar.githubmobileapp.utils.network_utils

import java.lang.Exception

sealed class ApiResult<out T> {
    data class Success<out T>(val data: T) : ApiResult<T>()
    data class Failure(val exception: Exception) : ApiResult<Nothing>()
}