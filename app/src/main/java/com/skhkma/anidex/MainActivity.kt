package com.skhkma.anidex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.compose.AniDexTheme
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.skhkma.anidex.navigation.AniDexNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AniDexTheme {
                AniDexNavHost(
                    isLoggedIn = Firebase.auth.currentUser != null
                )
            }
        }
    }
}
