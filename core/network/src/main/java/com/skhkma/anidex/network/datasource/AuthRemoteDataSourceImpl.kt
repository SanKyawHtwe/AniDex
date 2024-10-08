package com.skhkma.anidex.network.datasource

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

internal class AuthRemoteDataSourceImpl : AuthRemoteDataSource {

    override suspend fun signUpWithEmailPassword(email: String, password: String): Result<String> {
        return try {
            val authResult = Firebase.auth.createUserWithEmailAndPassword(email, password).await()
            val uid = authResult.user?.uid
            if (uid != null) {
                Result.success(authResult.user!!.uid)
            } else {
                Result.failure(Exception("Uid being null!"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    override suspend fun verifyEmail(): Result<Unit> {
        return try {
            val currentUser = Firebase.auth.currentUser
            currentUser!!.sendEmailVerification().await()
            return Result.success(Unit)
        }
        catch (e:Exception){
            Result.failure(e)
        }

    }

    override suspend fun isVerified():Boolean{
        return try {
            Firebase.auth.currentUser?.let {
                it.reload().await()
                it.isEmailVerified
            } ?: false
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun loginWithEmailPassword(email: String, password: String): Result<String> {
        return try {
            val authResult = Firebase.auth.signInWithEmailAndPassword(email, password).await()
            val uid = authResult.user?.uid
            if (uid != null) {
                Result.success(authResult.user!!.uid)
            } else {
                Result.failure(Exception("Uid being null!"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun logout() {
        Firebase.auth.signOut()
    }


}