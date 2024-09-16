package com.skhkma.anidex.data.repository

import com.skhkma.anidex.network.datasource.AuthRemoteDataSource

class ProfileRepositoryImpl(
    private val authRemoteDataSource: AuthRemoteDataSource
) : ProfileRepository {
    override suspend fun logout() {
        authRemoteDataSource.logout()
    }
}