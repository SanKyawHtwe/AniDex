package com.skhkma.anidex.data.repository

import com.skhkma.anidex.network.datasource.AuthRemoteDataSource


internal class AuthRepositoryImpl(
    private val authRemoteDataSource: AuthRemoteDataSource
) : AuthRepository {

    override suspend fun signUpWithEmailPassword(email: String, password: String): Result<String> {
        return authRemoteDataSource.signUpWithEmailPassword(email, password)
    }

    override suspend fun verifyEmail(): Result<Unit> {
        return authRemoteDataSource.verifyEmail()
    }

    override suspend fun isVerified(): Boolean {
        return authRemoteDataSource.isVerified()
    }

    override suspend fun loginWithEmailPassword(email: String, password: String): Result<String> {
        return authRemoteDataSource.loginWithEmailPassword(
            email = email,
            password = password
        )
    }

    override suspend fun logout() {
        authRemoteDataSource.logout()
    }
}
