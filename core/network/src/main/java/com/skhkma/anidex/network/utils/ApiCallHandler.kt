package com.skhkma.anidex.network.utils

import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.Serializable
import org.checkerframework.checker.units.qual.A

@Serializable
data class AnidexApiResponse<T>(
    val data: T?
)

internal suspend inline fun <reified T> handle(
    crossinline apiCall: suspend () -> HttpResponse
): Result<T> {
    return try {
        val httpResponse = apiCall()
        when (httpResponse.status) {
            //200
            HttpStatusCode.OK -> {
                val response: AnidexApiResponse<T> = httpResponse.body()
                if (response.data != null) {
                    return Result.success(response.data)
                } else {
                    return Result.failure(
                        ApiException(
                            code = httpResponse.status.value,
                            message = "Response data being null!"
                        )
                    )
                }
            }

            //404
            HttpStatusCode.NotFound -> Result.failure(
                ApiException(
                    code = httpResponse.status.value,
                    message = "NEW_USER"
                )
            )

            else -> Result.failure(
                ApiException(
                    code = httpResponse.status.value,
                    message = "Something went wrong"
                )
            )
        }
    } catch (e: Exception) {
        //Fail or Success?
        return Result.failure(e)
    }
}