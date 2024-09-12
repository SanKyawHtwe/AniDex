package com.skhkma.anidex.network.datasource

interface AuthRemoteDataSource {

    suspend fun signUpWithEmailPassword(email: String, password: String): Result<String>

    suspend fun verifyEmail(): Result<Unit>

    suspend fun isVerified(): Boolean

    suspend fun loginWithEmailPassword(email: String, password: String) : Result<String>

    suspend fun logout()

}