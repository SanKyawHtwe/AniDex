package com.skhkma.anidex.network.utils

import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode

internal suspend inline fun <reified T> handle(
    crossinline apiCall: suspend () -> HttpResponse
): Result<T> {
    return try {
        val httpResponse = apiCall()
        when (httpResponse.status) {
            //200
            HttpStatusCode.OK -> {
                //Success?
                val response: T = httpResponse.body()
                if (response != null) {
                    return Result.success(response)
                }

                return Result.failure(
                    ApiException(
                        code = httpResponse.status.value,
                        message = "Something went wrong"
                    )
                )
                //Fail or Success?

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