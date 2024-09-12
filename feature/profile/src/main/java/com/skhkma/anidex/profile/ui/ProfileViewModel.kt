package com.skhkma.anidex.profile.ui

import androidx.lifecycle.ViewModel
import com.skhkma.anidex.data.repository.AuthRepository
import com.skhkma.anidex.data.repository.ProfileRepository

class ProfileViewModel(
    private val profileRepository: ProfileRepository
): ViewModel() {

    suspend fun logout(){
        profileRepository.logout()
    }
}