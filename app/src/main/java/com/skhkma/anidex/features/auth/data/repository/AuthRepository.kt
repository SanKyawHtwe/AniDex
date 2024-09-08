package com.skhkma.anidex.features.auth.data.repository

interface AuthRepository {

    suspend fun signUpWithEmailPassword(email: String, password: String): Result<String>

    suspend fun verifyEmail(): Result<Unit>

    suspend fun isVerified(): Boolean

    suspend fun loginWithEmailPassword(email: String, password: String): Result<String>
}