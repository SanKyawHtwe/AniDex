package com.skhkma.anidex.features.auth.data.datasource

interface AuthRemoteDataSource {

    suspend fun signUpWithEmailPassword(email: String, password: String): Result<String>

    suspend fun verifyEmail(): Result<Unit>

}