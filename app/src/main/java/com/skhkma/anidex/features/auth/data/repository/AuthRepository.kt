package com.skhkma.anidex.features.auth.data.repository

interface AuthRepository {

    suspend fun signUpWithEmailPassword(email: String, password: String): Result<String>

}