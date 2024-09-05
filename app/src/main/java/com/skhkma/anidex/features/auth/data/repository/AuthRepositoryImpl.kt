package com.skhkma.anidex.features.auth.data.repository

import com.skhkma.anidex.features.auth.data.datasource.AuthRemoteDataSource

class AuthRepositoryImpl(private val authRemoteDataSource: AuthRemoteDataSource) : AuthRepository {

    override suspend fun signUpWithEmailPassword(email: String, password: String): Result<String> {
        return authRemoteDataSource.signUpWithEmailPassword(email, password)
    }

    override suspend fun verifyEmail(): Result<Unit> {
        return authRemoteDataSource.verifyEmail()
    }

    override suspend fun isVerified(): Boolean {
         return authRemoteDataSource.isVerified()
    }
}
